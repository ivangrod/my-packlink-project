package com.packlink.mypacklinkproject.configuration

import java.io.File
import org.springframework.context.{ApplicationContextInitializer, ConfigurableApplicationContext}
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait

import java.time.Duration

class TestApplicationContextInitializer extends ApplicationContextInitializer[ConfigurableApplicationContext] {

  override def initialize(applicationContext: ConfigurableApplicationContext): Unit =
    if (isLocalProfileActive(applicationContext)) {
      startLocalInfrastructure()
    }

  private def isLocalProfileActive(applicationContext: ConfigurableApplicationContext): Boolean = {
    val environment = applicationContext.getEnvironment()
    val profiles    = environment.getActiveProfiles()

    profiles.contains("it-local")
  }

  private def startLocalInfrastructure(): Unit = {
    val configuration = new File("src/it/resources/infrastructure-it-local.yml")
    val container     = new DockerComposeContainer(configuration)

    container.waitingFor("rabbitmq", WaitingStrategies.rabbitmqStrategy)

    container.start()
  }

  object WaitingStrategies {

    private val rabbitmqStartedLog: String = ".*Server startup complete.*"
    private val rabbitmqTimeout: Duration  = Duration.ofSeconds(20)

    val rabbitmqStrategy = Wait.forLogMessage(rabbitmqStartedLog, 1).withStartupTimeout(rabbitmqTimeout)
  }
}
