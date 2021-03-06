package com.packlink.mypacklinkproject

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.BeforeAndAfter
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

trait BaseUnitSpec
    extends AnyFunSpec
    with BeforeAndAfter
    with Matchers
    with MockitoSugar
    with ArgumentMatchersSugar
