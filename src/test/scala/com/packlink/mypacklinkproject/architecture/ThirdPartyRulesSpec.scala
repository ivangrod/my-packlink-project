package com.packlink.mypacklinkproject.architecture

class ThirdPartyRulesSpec extends BaseArchitectureSpec {

  test("Classes only should import allowed third party dependencies") {
    val rule = scalaClasses.should.onlyAccessClassesThat
      .resideInAnyPackage(
        s"${ArchitectureSpecSettings.RootApplicationPackage}..",
        "java..",
        "javax..",
        "scala..",
        "cats..",
        "org.springframework.core..",
        "org.springframework.context..",
        "org.springframework.boot..",
        "org.springframework.web..",
        "org.springframework.http..",
        "org.springframework.data.repository..",
        "org.springframework.util..",
        "org.springframework.cloud..",
        "io.micrometer.core.instrument..",
        "io.micrometer.stackdriver..",
        "com.fasterxml.jackson..",
        "org.slf4j..",
        "scoverage.."
      )

    rule.check(applicationClasses)
  }
}
