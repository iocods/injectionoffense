// File: app/MyApplicationLoader.scala
import play.api._
import play.api.inject.guice._
import play.api.inject._
import play.api.inject.ApplicationLifecycle

import java.io.{File, PrintWriter}

class AppLoader extends GuiceApplicationLoader() {
	
  override def builder(context: ApplicationLoader.Context): GuiceApplicationBuilder = {
    val appBuilder = super.builder(context)
      .overrides(bind[InitializationLogic].toSelf.eagerly())

     
    val app = appBuilder.build()

    // Add a stop hook to execute cleanup logic
    app.injector.instanceOf[ApplicationLifecycle].addStopHook { () =>
      InitializationLogic.deleteFiles()
      // You can also perform other cleanup tasks here if needed
      println("Executing cleanup logic when the application stops")
      scala.concurrent.Future.successful(())
    }

    appBuilder
  }
}
