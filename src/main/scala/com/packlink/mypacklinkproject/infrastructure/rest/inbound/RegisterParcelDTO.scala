package com.packlink.mypacklinkproject.infrastructure.rest.inbound

case class RegisterParcelDTO(
  var parcelUUID: String,
  var width: Int,
  var height: Int,
  var length: Int,
  var weight: Int
)
