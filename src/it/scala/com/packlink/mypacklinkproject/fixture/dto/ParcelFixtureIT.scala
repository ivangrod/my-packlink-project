package com.packlink.mypacklinkproject.fixture.dto

import com.packlink.mypacklinkproject.infrastructure.rest.inbound.RegisterParcelDTO

import java.util.UUID

object ParcelFixtureIT {

  val anyParcelReadyToRegistration: RegisterParcelDTO =
    RegisterParcelDTO(UUID.randomUUID().toString, 20, 20, 20, 20);
}
