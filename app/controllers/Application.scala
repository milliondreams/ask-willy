package controllers

import play.api._
import libs.json.JsValue
import play.api.mvc._
import scala.compat.Platform.getClassForName

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }


}