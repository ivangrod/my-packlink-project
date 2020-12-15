package com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq

import java.time.Clock
import java.util.UUID
import cats.effect.Sync
import cats.implicits._
import com.packlink.mypacklinkproject.domain.event.{Event, EventPublisher}
import com.packlink.mypacklinkproject.infrastructure.framework.configuration.properties.{AmqpMessageRoute, AmqpProperties}
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.event.EventConverterResolver

import scala.jdk.CollectionConverters._
import scala.reflect.ClassTag

class AmqpEventPublisher[F[_]: Sync](
  amqpPublisher: AmqpPublisher[F],
  amqpProperties: AmqpProperties,
  eventConverterResolver: EventConverterResolver[F],
  clock: Clock
) extends EventPublisher[F] {
  override def publish(message: Event): F[Unit] =
    for {
      configurationRoute <- getRouteForEventOrRaiseError(message.getClass.getName)
      _                  <- publishEvent(message, configurationRoute)
    } yield ()

  private def getRouteForEventOrRaiseError(name: String): F[AmqpMessageRoute] =
    Sync[F].delay(amqpProperties.events.asScala.get(name).get)

  private def publishEvent(message: Event, amqpEventRoute: AmqpMessageRoute): F[Unit] =
    for {
      maybeEventConverter <-
        Sync[F].delay(eventConverterResolver.getEventConverterByEventType(ClassTag(message.getClass)))
      eventConverter <- maybeEventConverter.liftTo[F](new RuntimeException)
      messageDto     <- eventConverter.toDto(message)
      headers        <- Sync[F].delay(createHeaders(amqpEventRoute.messageType))
      _              <- amqpPublisher.publish(messageDto, headers, amqpEventRoute)
    } yield ()

  private def createHeaders[T](eventType: String): Map[String, String] = {
    Map(
      AmqpEventHeader.MessageId  -> UUID.randomUUID().toString,
      AmqpEventHeader.Type       -> eventType,
      AmqpEventHeader.EmittedBy  -> amqpProperties.emittedBy,
      AmqpEventHeader.OccurredOn -> clock.instant().toString
    )
  }

}
object AmqpEventHeader {
  val MessageId: String      = "x-message-id"
  val Type: String           = "x-type"
  val EmittedBy: String      = "x-emitted-by"
  val OccurredOn: String     = "x-occurred-on"
  val CorrelationId: String  = "x-correlation-id"
  val RequestContext: String = "X-Request-Context"
}
