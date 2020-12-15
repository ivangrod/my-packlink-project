package com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.converter

import com.packlink.mypacklinkproject.domain.event.Message
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.MessageDTO

import scala.reflect.{ClassTag, classTag}

abstract class MessageConverter[F[_], A <: Message: ClassTag, B <: MessageDTO] {
  def toDto(message: A): F[B]
  def messageType: ClassTag[A] = classTag[A]
}
