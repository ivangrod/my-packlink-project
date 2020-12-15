package com.packlink.mypacklinkproject.infrastructure.rest.inbound

import cats.effect.IO
import com.packlink.mypacklinkproject.application.parcel.create.CreatorParcelUseCase
import com.packlink.mypacklinkproject.domain.model.Parcel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{PostMapping, RequestBody, RequestMapping, RestController}

import java.net.URI

@RestController
@RequestMapping(Array("api/parcels"))
class ParcelController(creatorParcelUseCase: CreatorParcelUseCase[IO]) {

  @PostMapping
  def create(@RequestBody parcelToRegister: RegisterParcelDTO): ResponseEntity[Nothing] =
    createParcel(parcelToRegister).unsafeRunSync()

  private def createParcel(parcelToRegister: RegisterParcelDTO) = {
    for {
      parcel <- Parcel.create[IO](
        parcelToRegister.parcelUUID,
        parcelToRegister.width,
        parcelToRegister.height,
        parcelToRegister.length,
        parcelToRegister.weight
      )
      _ <- creatorParcelUseCase.registerParcel(parcel)
    } yield ResponseEntity.created(URI.create("api/parcels/" + parcel.id)).build()
  }
}
