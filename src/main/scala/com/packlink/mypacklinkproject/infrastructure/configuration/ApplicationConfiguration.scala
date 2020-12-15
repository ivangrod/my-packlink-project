package com.packlink.mypacklinkproject.infrastructure.configuration

import cats.effect.IO
import com.packlink.mypacklinkproject.application.parcel.create.CreatorParcelUseCase
import com.packlink.mypacklinkproject.infrastructure.framework.rabbitmq.AmqpEventPublisher
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class ApplicationConfiguration {

  @Bean
  def creatorParcelUseCase(
    eventPublisher: AmqpEventPublisher[IO]
  ): CreatorParcelUseCase[IO] =
    new CreatorParcelUseCase[IO](eventPublisher)
}
