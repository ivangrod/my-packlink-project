package com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq

import cats.effect.Sync
import cats.implicits._
import com.fasterxml.jackson.databind.ObjectMapper
import com.packlink.mypacklinkproject.infrastructure.framework.configuration.properties.AmqpMessageRoute
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.MessageDTO
import org.springframework.amqp.core.{AmqpTemplate, Message, MessageBuilder, MessageProperties}

import scala.jdk.CollectionConverters._

class AmqpPublisher[F[_]: Sync](
  amqpTemplate: AmqpTemplate,
  objectMapper: ObjectMapper
) extends AmqpMessagePublisher[F] {
  override def publish(
    message: MessageDTO,
    headers: Map[String, String],
    amqpMessageRoute: AmqpMessageRoute
  ): F[Unit] =
    for {
      messageToPublish <- Sync[F].delay(createMessageToPublish(message, headers))
      _ <- amqpMessageRoute.routingKeys.asScala.toList
        .traverse(publishWithRoutingKey(amqpMessageRoute.exchange, _, messageToPublish))
    } yield ()

  private def createMessageToPublish(message: MessageDTO, headers: Map[String, String]): Message = {
    val body = objectMapper.writeValueAsBytes(message)

    val messageProperties: MessageProperties = new MessageProperties()
    headers.foreachEntry((name, value) => messageProperties.setHeader(name, value))

    messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON)
    messageProperties.setContentLength(body.length.toLong)

    MessageBuilder.withBody(body).andProperties(messageProperties).build()
  }

  private def publishWithRoutingKey(exchange: String, routingKey: String, messageToPublish: Message): F[Unit] =
    for {
      _ <- Sync[F].delay(amqpTemplate.send(exchange, routingKey, messageToPublish))
    } yield ()
}
