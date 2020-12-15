package com.packlink.mypacklinkproject.domain.event.parcel

import com.packlink.mypacklinkproject.domain.event.Event
import com.packlink.mypacklinkproject.domain.model.Parcel

case class ParcelCreated(parcelId: String) extends Event

object ParcelCreated {
  def apply(parcel: Parcel): ParcelCreated = ParcelCreated(parcel.id)
}
