package com.packlink.mypacklinkproject.architecture

import com.packlink.mypacklinkproject.domain.event.Event
import org.springframework.boot.context.properties.ConfigurationProperties

class NamingConventionSpec extends BaseArchitectureSpec {

  test("Controllers should follow the pattern <name>Controller") {
    val rule = scalaClassesThat.resideInAPackage("..controller").should.haveSimpleNameEndingWith("Controller")

    rule.check(applicationClasses)
  }

  test("Services should follow the pattern <name>Service") {
    val rule = scalaClassesThat.resideInAPackage("..service..").should.haveSimpleNameEndingWith("Service")

    rule.check(applicationClasses)
  }

  test("Repositories should follow the pattern <name>Repository") {
    val rule = scalaClassesThat.resideInAPackage("..repository").should.haveSimpleNameEndingWith("Repository")

    rule.check(applicationClasses)
  }

  test("Factories should follow the pattern <name>Factory") {
    val rule = scalaClassesThat.resideInAPackage("..factory").should.haveSimpleNameEndingWith("Factory")

    rule.check(applicationClasses)
  }

  test("Converters should follow the pattern <name>Converter") {
    val rule = scalaClassesThat.resideInAPackage("..converter").should.haveSimpleNameEndingWith("Converter")

    rule.check(applicationClasses)
  }

  test("DTOs should follow the pattern <name>Dto") {
    val rule = scalaClassesThat.resideInAPackage("..dto").should.haveSimpleNameEndingWith("Dto")

    rule.check(applicationClasses)
  }

  test("Events should follow the pattern <name>Event") {
    val rule = scalaClassesThat
      .resideInAPackage("..event")
      .and
      .implement(classOf[Event])
      .should
      .haveSimpleNameEndingWith("Event")

    rule.check(applicationClasses)
  }

  test("Listeners should follow the pattern <name>Listener") {
    val rule = scalaClassesThat.resideInAPackage("..listener").should.haveSimpleNameEndingWith("Listener")

    rule.check(applicationClasses)
  }

  test("Exceptions should follow the pattern <name>Exception") {
    val rule = scalaClassesThat.resideInAPackage("..exception").should.haveSimpleNameEndingWith("Exception")

    rule.check(applicationClasses)
  }

  test("Configurations should follow the pattern <name>Configuration") {
    val rule = scalaClassesThat.resideInAPackage("..configuration").should.haveSimpleNameEndingWith("Configuration")

    rule.check(applicationClasses)
  }

  test("Properties should follow the pattern <name>Properties") {
    val rule = scalaClassesThat
      .resideInAPackage("..properties")
      .and
      .implement(classOf[ConfigurationProperties])
      .should
      .haveSimpleNameEndingWith("Properties")

    rule.check(applicationClasses)
  }
}
