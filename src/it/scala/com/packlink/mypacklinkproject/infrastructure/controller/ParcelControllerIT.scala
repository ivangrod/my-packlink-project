package com.packlink.mypacklinkproject.infrastructure.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.packlink.mypacklinkproject.BaseIntegrationSpec
import com.packlink.mypacklinkproject.fixture.dto.ParcelFixtureIT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ParcelControllerIT extends BaseIntegrationSpec {

  @Autowired
  private var objectMapper: ObjectMapper = _

  private var mockMvc: MockMvc = _

  before {
    mockMvc = buildMockMvc()
  }

  describe("Register Parcel") {

    it("should register a parcel") {
      val parcelToRegister = ParcelFixtureIT.anyParcelReadyToRegistration
      val result = mockMvc.perform(
        post("/api/parcels")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(parcelToRegister))
      )

      result.andExpect(MockMvcResultMatchers.status().isCreated).andExpect(MockMvcResultMatchers.header().string("Location", s"api/parcels/${parcelToRegister.parcelUUID}"))
    }
  }
}
