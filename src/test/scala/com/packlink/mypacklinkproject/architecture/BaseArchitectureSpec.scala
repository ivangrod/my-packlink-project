package com.packlink.mypacklinkproject.architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.{ClassFileImporter, ImportOption, Location}
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.{classes, noClasses}
import com.tngtech.archunit.lang.syntax.elements.{ClassesThat, GivenClassesConjunction}
import org.scalatest.funsuite.AnyFunSuite

import scala.reflect.ScalaSignature

object ArchitectureSpecSettings {

  lazy val RootApplicationPackage: String = "com.packlink.mypacklinkproject"

  lazy val ScalaTestsLocationPattern = java.util.regex.Pattern.compile(".*/target/scala([^/]+)/test-classes/.*")
}

abstract class BaseArchitectureSpec extends AnyFunSuite {

  protected lazy val applicationClasses: JavaClasses = new ClassFileImporter()
    .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
    .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
    .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
    .withImportOption(new DoNotIncludeScalaTests)
    .importPackages(ArchitectureSpecSettings.RootApplicationPackage)

  protected lazy val scalaClasses: GivenClassesConjunction =
    classes.that.areAnnotatedWith(classOf[ScalaSignature])

  protected lazy val noScalaClasses: GivenClassesConjunction =
    noClasses.that.areAnnotatedWith(classOf[ScalaSignature])

  protected lazy val scalaClassesThat: ClassesThat[GivenClassesConjunction] =
    scalaClasses.and

  protected lazy val noScalaClassesThat: ClassesThat[GivenClassesConjunction] =
    noScalaClasses.and
}

sealed class DoNotIncludeScalaTests extends ImportOption {

  override def includes(location: Location): Boolean =
    !location.matches(ArchitectureSpecSettings.ScalaTestsLocationPattern)
}
