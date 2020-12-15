package com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.event

import cats.effect.Sync
import com.packlink.mypacklinkproject.domain.event.Event
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.converter.event.EventConverter

import scala.reflect.ClassTag

class EventConverterResolver[F[_]: Sync](eventConverters: List[EventConverter[F, Event, EventDTO]]) {

  private val convertersMap = eventConverters.groupBy(_.messageType).view.mapValues(_.head)

  def getEventConverterByEventType(eventType: ClassTag[Event]): Option[EventConverter[F, Event, EventDTO]] =
    convertersMap.get(eventType)
}
