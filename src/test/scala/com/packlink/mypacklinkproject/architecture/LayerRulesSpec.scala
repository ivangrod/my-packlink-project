package com.packlink.mypacklinkproject.architecture

class LayerRulesSpec extends BaseArchitectureSpec {

  test("Domain layer should not access to any other layer") {
    val rule = noScalaClassesThat
      .resideInAPackage("..domain..")
      .should
      .accessClassesThat()
      .resideInAnyPackage("..application..", "..infrastructure..")

    rule.check(applicationClasses)
  }

  test("Application layer should not access to the infrastructure layer") {
    val rule = noScalaClassesThat
      .resideInAPackage("..application..")
      .should
      .accessClassesThat()
      .resideInAPackage("..infrastructure..")

    rule.check(applicationClasses)
  }
}