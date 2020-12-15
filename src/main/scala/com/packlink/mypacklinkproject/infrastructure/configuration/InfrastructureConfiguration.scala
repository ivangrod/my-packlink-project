package com.packlink.mypacklinkproject.infrastructure.configuration

import cats.effect.IO
import com.fasterxml.jackson.databind.ObjectMapper
import com.packlink.mypacklinkproject.domain.event.Event
import com.packlink.mypacklinkproject.infrastructure.framework.configuration.properties.AmqpProperties
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.converter.event.{EventConverter, ParcelCreatedConverter}
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.event.{EventConverterResolver, EventDTO}
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.{AmqpEventPublisher, AmqpPublisher}
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.context.annotation.{Bean, Configuration}

import java.time.Clock
import scala.jdk.CollectionConverters._

@Configuration
class InfrastructureConfiguration {

  @Bean
  def clock: Clock = Clock.systemDefaultZone()

  @Bean
  def amqpPublisher(
    amqpTemplate: AmqpTemplate,
    objectMapper: ObjectMapper
  ): AmqpPublisher[IO] =
    new AmqpPublisher[IO](amqpTemplate, objectMapper)

  @Bean
  def contractCreatedEventConverter(): EventConverter[IO, Event, EventDTO] =
    new ParcelCreatedConverter[IO]().asInstanceOf[EventConverter[IO, Event, EventDTO]]

  @Bean
  def eventConverterResolver(
    eventConverters: java.util.List[EventConverter[IO, Event, EventDTO]]
  ): EventConverterResolver[IO] =
    new EventConverterResolver[IO](eventConverters.asScala.toList)

  @Bean
  def amqpEventPublisher(
    amqpPublisher: AmqpPublisher[IO],
    amqpProperties: AmqpProperties,
    eventConverterResolver: EventConverterResolver[IO],
    clock: Clock
  ) = new AmqpEventPublisher[IO](amqpPublisher, amqpProperties, eventConverterResolver, clock)
}
