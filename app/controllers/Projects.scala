/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/20/12
 * Time: 7:38 AM
 * To change this template use File | Settings | File Templates.
 */
package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.{Json, JsValue}
import models.Project

import com.codahale.jerkson.Json._


object Projects extends Controller {

  /* POST */
  def postProject=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{project=>

      val projectId=Project.addProject(Project((project\"name").toString(),(project\"description").toString(),(project\"status").toString()));
      Ok(Json.stringify(
        Json.toJson(Map("id" -> projectId))
      ))}.getOrElse{
      BadRequest("failed to add project")
    }
  }

  /* GET */
  def getAllProjects=Action{
    val projects=Project.getAll
    Ok(generate(projects))
  }

  /* GET */
  def getAllActiveProjects=Action{
    val projects=Project.getByStatus("ACTIVE")
    Ok(generate(projects))
  }

  /* GET */
  def getAllClosedProjects=Action{
    val projects=Project.getByStatus("CLOSED")
    Ok(generate(projects))
  }

  /* GET */
  def getAllPipelinedProjects=Action{
    val projects=Project.getByStatus("PIPELINE")
    Ok(generate(projects))
  }

  /* GET */
  def getProjectByName(name:String)=Action{
      val project=Project.findProjectByName(name)
      Ok(generate(project))
  }

  /* GET */
  def getProjectById(projectId:Long)=Action{
      val project=Project.findProjectById(projectId)
      Ok(generate(project))
  }

  /* DELETE */
  def deleteProject(projectId:Long)=Action{
    val removed=Project.findProjectById(projectId)
    if (Project.removeProjectById(projectId))
      Ok(generate(removed))
    else
      Ok("failed to remove project")
  }

}
