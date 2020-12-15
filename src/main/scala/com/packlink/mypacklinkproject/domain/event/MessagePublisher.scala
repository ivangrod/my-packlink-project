package com.packlink.mypacklinkproject.domain.event

trait MessagePublisher[F[_], T <: Message] {
  def publish(message: T): F[Unit]
}

