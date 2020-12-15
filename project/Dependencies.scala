import sbt._

object Version {
  val springBoot     = "2.3.5.RELEASE"
  val springCloudGcp = "1.2.6.RELEASE"
  val micrometer     = "1.6.0"
  val janino         = "3.1.2"
  val jackson        = "2.11.3"
  val cats           = "2.2.0"
  val catsEffect     = "2.2.0"
  val scalatest      = "3.2.2"
  val mockitoScala   = "1.16.2"
  val testContainers = "1.15.0"
  val archUnit       = "0.14.1"
}

object Library {

  def springBootModule(artifact: String): ModuleID     = "org.springframework.boot"  % artifact % Version.springBoot
  def springCloudGcpModule(artifact: String): ModuleID = "org.springframework.cloud" % artifact % Version.springCloudGcp

  lazy val springBoot           = springBootModule("spring-boot-starter")
  lazy val springBootActuator   = springBootModule("spring-boot-starter-actuator")
  lazy val springBootValidation = springBootModule("spring-boot-starter-validation")

  lazy val springBootWeb =
    springBootModule("spring-boot-starter-web") exclude ("org.springframework.boot", "spring-boot-starter-tomcat")
  lazy val springBootJetty      = springBootModule("spring-boot-starter-jetty")
  lazy val springBootAmqp       = springBootModule("spring-boot-starter-amqp")

  lazy val janino                = "org.codehaus.janino" % "janino" % Version.janino

  lazy val jacksonScalaModule = "com.fasterxml.jackson.module" %% "jackson-module-scala" % Version.jackson
  lazy val cats               = "org.typelevel"                %% "cats-core"            % Version.cats
  lazy val catsEffect         = "org.typelevel"                %% "cats-effect"          % Version.catsEffect
  lazy val springBootTest = springBootModule("spring-boot-starter-test") % "it, test" exclude ("org.mockito", "mockito-core")

  lazy val scalaTest      = "org.scalatest"        %% "scalatest"     % Version.scalatest      % "test, it"
  lazy val mockitoScala   = "org.mockito"          %% "mockito-scala" % Version.mockitoScala   % "test, it"
  lazy val testContainers = "org.testcontainers"   % "testcontainers" % Version.testContainers % "it"
  lazy val archUnit       = "com.tngtech.archunit" % "archunit"       % Version.archUnit       % "test"

}
