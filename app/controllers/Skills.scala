/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/21/12
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.{Json, JsValue}

import models.Skill


object Skills extends Controller{

  /* POST */
  def postSkill=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{skill=>
      val skillId=Skill.addSkill((skill\"skillName").toString)
      Ok(Json.stringify(
        Json.toJson(Map("skillId"->skillId))))
    }.getOrElse{
      BadRequest("failed to add skill")
    }
  }

}
