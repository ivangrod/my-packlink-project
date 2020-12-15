package com.packlink.mypacklinkproject.infrastructure.framework.configuration.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated

import java.util.Collections
import scala.beans.BeanProperty

@Validated
@Configuration
@ConfigurationProperties(prefix = "amqp.out")
class AmqpProperties {

  @Value("${spring.application.name}")
  var emittedBy: String = _

  @BeanProperty
  var events: java.util.Map[String, AmqpMessageRoute] = Collections.emptyMap()
}

@Validated
class AmqpMessageRoute {

  @BeanProperty
  var exchange: String = _

  @BeanProperty
  var routingKeys: java.util.List[String] = Collections.emptyList()

  @BeanProperty
  var messageType: String = _

}
