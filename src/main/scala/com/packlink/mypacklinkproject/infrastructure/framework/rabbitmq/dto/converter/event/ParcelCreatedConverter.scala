package com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.converter.event

import cats.effect.Sync
import com.packlink.mypacklinkproject.domain.event.parcel.ParcelCreated
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.dto.event.ParcelCreatedDTO

class ParcelCreatedConverter[F[_]: Sync] extends EventConverter[F, ParcelCreated, ParcelCreatedDTO] {
  override def toDto(parcelCreatedEvent: ParcelCreated): F[ParcelCreatedDTO] =
    Sync[F].delay(
      ParcelCreatedDTO(
        parcelId = parcelCreatedEvent.parcelId
      )
    )
}
