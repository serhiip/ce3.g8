ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.13.5"

lazy val root = (project in file(".")).settings(
  name := "$name;format="norm"$",
  libraryDependencies ++= Seq(
    // "core" module - IO, IOApp, schedulers
    // This pulls in the kernel and std modules automatically.
    "org.typelevel" %% "cats-effect" % "3.0.1",
    // concurrency abstractions and primitives (Concurrent, Sync, Async etc.)
    "org.typelevel" %% "cats-effect-kernel" % "3.0.1",
    // standard "effect" library (Queues, Console, Random etc.)
    "org.typelevel" %% "cats-effect-std" % "3.0.1"$if(scala3.truthy)$$else$,
    // better monadic for compiler plugin as suggested by documentation
    compilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")$endif$$if(testlib-use-cats-effect-testing-specs2.truthy)$,

    "org.typelevel" %% "cats-effect-testing-specs2" % "1.0.0" % Test$else$

    $endif$$if(testlib-use-munit-cats-effect-3.truthy)$,
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.0" % Test$else$$endif$$if(testlib-use-weaver-test.truthy)$,
    "com.disneystreaming" %% "weaver-framework" % "0.7.0-M7" % Test$else$$endif$

  )$if(testlib-use-munit-cats-effect-3.truthy || testlib-use-weaver-test.truthy)$,

  testFrameworks ++= Seq($if(testlib-use-munit-cats-effect-3.truthy)$
    new TestFramework("munit.Framework")$else$$endif$$if(testlib-use-weaver-test.truthy && testlib-use-munit-cats-effect-3.truthy)$,$else$$endif$
    $if(testlib-use-weaver-test.truthy)$
    new TestFramework("weaver.framework.TestFramework")$else$$endif$

  )$else$$endif$

)
