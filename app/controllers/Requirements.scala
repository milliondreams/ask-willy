/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/20/12
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */
package controllers

import play.api.mvc.{Controller, Action}
import play.api.libs.json.{Json, JsValue}
import models.Requirement
import java.util.Date
import com.codahale.jerkson.Json._


object Requirements extends Controller{

  /* POST */
  def postRequirement=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{requirement=>
      val requirementId=Requirement.addRequirement(Requirement((requirement\"projectId").as[Long],(requirement\"contactId").as[Long],(requirement\"fillBy").as[Date],(requirement\"priority").as[String],(requirement\"category").as[String]));
      Ok(Json.stringify(
        Json.toJson(Map("id" -> requirementId))
      ))}.getOrElse{
      BadRequest("failed to add requirement")
    }
  }

  /* GET */
  def getAllReq=Action{
    val requirements=Requirement.getRequirements
    if(requirements.isEmpty)
      BadRequest("No requirements")
    Ok(generate(requirements))
  }

  /* GET */
  def getCrucialReq=Action{
    val requirements=Requirement.getReqByPriority("CRUCIAL")
    if(requirements.isEmpty)
      BadRequest("No requirements")
    Ok(generate(requirements))
  }

  /* GET */
  def getNormalReq=Action{
    val requirements=Requirement.getReqByPriority("NORMAL")
    if(requirements.isEmpty)
      BadRequest("No requirements")
    Ok(generate(requirements))
  }

  /* GET */
  def getLowReq=Action{
    val requirements=Requirement.getReqByPriority("LOW")
    if(requirements.isEmpty)
      BadRequest("No requirements")
    Ok(generate(requirements))
  }

  /* GET */
  def getBilledReq=Action{
    val requirements=Requirement.getReqByCategory("BILLED")
    if(requirements.isEmpty)
      BadRequest("No requirements")
    Ok(generate(requirements))
  }

  /* GET */
  def getShadowReq=Action{
    val requirements=Requirement.getReqByCategory("SHADOW")
    if(requirements.isEmpty)
      BadRequest("No requirements")
    Ok(generate(requirements))
  }

  /* GET */
  def getPitchReq=Action{
    val requirements=Requirement.getReqByCategory("PITCH")
    if(requirements.isEmpty)
      BadRequest("No requirements")
    Ok(generate(requirements))
  }

  /* GET */
  def getTentativeReq=Action{
    val requirements=Requirement.getReqByCategory("TENTATIVE")
    if(requirements.isEmpty)
      BadRequest("No requirements")
    Ok(generate(requirements))
  }

  /* DELETE */
  def deleteRequirement(requirementId:Long)=Action{
    val requirement=Requirement.findById(requirementId)
    if (requirement.isEmpty)
      Ok(Json.toJson(generate(requirement)))
    else BadRequest("failed to remove person")
  }
}
