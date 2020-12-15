package com.packlink.mypacklinkproject.domain.model

import cats.effect.Sync

case class Parcel(var id: String, var width: Int, var height: Int, var length: Int, var weight: Int)

object Parcel {

  def create[F[_]: Sync](id: String, width: Int, height: Int, length: Int, weight: Int): F[Parcel] =
    Sync[F].delay(Parcel(id = id, width = width, height = height, length = length, weight = weight))
}
