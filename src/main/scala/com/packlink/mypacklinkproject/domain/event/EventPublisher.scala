package com.packlink.mypacklinkproject.domain.event

trait EventPublisher[F[_]] extends MessagePublisher[F, Event] {

}
