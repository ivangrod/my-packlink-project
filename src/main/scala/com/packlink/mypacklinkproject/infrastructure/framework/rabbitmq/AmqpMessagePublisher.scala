package com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq

import com.packlink.mypacklinkproject.infrastructure.framework.configuration.properties.AmqpMessageRoute
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.MessageDTO

trait AmqpMessagePublisher[F[_]] {
  def publish(message: MessageDTO, headers: Map[String, String], amqpMesssageRoute: AmqpMessageRoute): F[Unit]
}
