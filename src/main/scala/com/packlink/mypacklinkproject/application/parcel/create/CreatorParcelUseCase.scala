package com.packlink.mypacklinkproject.application.parcel.create

import cats.effect.Sync
import com.packlink.mypacklinkproject.domain.event.EventPublisher
import com.packlink.mypacklinkproject.domain.event.parcel.ParcelCreated
import com.packlink.mypacklinkproject.domain.model.Parcel

class CreatorParcelUseCase[F[_]: Sync](eventPublisher: EventPublisher[F]) {

  def registerParcel(parcel: Parcel): F[Unit] = eventPublisher.publish(ParcelCreated(parcel))
}
