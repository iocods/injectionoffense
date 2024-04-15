package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import service.CommandService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class CommandController @Inject()(val controllerComponents: ControllerComponents, val commandService: CommandService) extends BaseController {
  var result: String = ""
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(commandService.fileList(), result))
  }

  def delete() = Action { implicit request: Request[AnyContent] => 
    var filename = ""
    request.getQueryString("filename") match{
      case Some(value) => filename = value
      case None => println("Not present")
    }
    result = commandService.processCommand(filename)
    Redirect(routes.CommandController.index())
    // Ok(views.html.index(commandService.fileList(), result))
  }
}
