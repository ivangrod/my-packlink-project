package com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.converter.event

import com.packlink.mypacklinkproject.domain.event.Event
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.converter.MessageConverter
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.event.EventDTO

trait EventConverter[F[_], A <: Event, B <: EventDTO] extends MessageConverter[F, A, B]