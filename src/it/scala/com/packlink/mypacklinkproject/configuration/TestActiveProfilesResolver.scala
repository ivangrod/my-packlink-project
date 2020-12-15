package com.packlink.mypacklinkproject.configuration

import org.springframework.test.context.ActiveProfilesResolver

class TestActiveProfilesResolver extends ActiveProfilesResolver {

  override def resolve(testClass: Class[_]): Array[String] =
    if (isLocalEnvironment())
      Array("it", "it-local")
    else
      Array("it")

  private def isLocalEnvironment(): Boolean =
    Option(System.getenv("CI")).getOrElse("false") == "false"
}
