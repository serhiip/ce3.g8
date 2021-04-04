package $package$

import weaver.SimpleIOSuite
import cats.effect._
import org.specs2.matcher.MatchResult

// Suites must be "objects" for them to be picked by the framework
object HelloWorldWeaverSuite extends SimpleIOSuite {

  pureTest("pureTest { 1 must beEqualTo(1) }") {
    1 must beEqualTo(1)
  }
}
