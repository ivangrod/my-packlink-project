package com.packlink.mypacklinkproject

import com.packlink.mypacklinkproject.configuration.{TestActiveProfilesResolver, TestApplicationContextInitializer}
import org.mockito.MockitoSugar
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration, TestContextManager}
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ContextConfiguration(initializers = Array(classOf[TestApplicationContextInitializer]))
@ActiveProfiles(resolver = classOf[TestActiveProfilesResolver])
@SpringBootTest(
  classes = Array(classOf[MypacklinkprojectApplication]),
  webEnvironment = WebEnvironment.RANDOM_PORT
)
abstract class BaseIntegrationSpec
    extends AnyFunSpec
    with BeforeAndAfter
    with BeforeAndAfterAll
    with MockitoSugar
    with Matchers {

  @Autowired
  private var wac: WebApplicationContext = _

  protected val testContextManager: TestContextManager = new TestContextManager(this.getClass)

  override def beforeAll(): Unit = testContextManager.prepareTestInstance(this)

  protected def buildMockMvc(): MockMvc =
    MockMvcBuilders
      .webAppContextSetup(wac)
      .build()
}
