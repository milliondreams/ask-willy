/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Projects(name:String,description:String,status:String)

object Projects{

  def simple={
    get[String]("name")~
    get[String]("description")~
    get[String]("status")map{
      case name~description~status=>Projects(name,description,status)
    }
  }

  def addProject(project:Projects):Option[Long]={
    DB.withConnection{implicit connection=>
      SQL("INSERT INTO projects(name,description,status) VALUES({name},{description},{status})")
        .on(
        'name->project.name,
        'description->project.description,
        'status->project.status
      ).executeInsert()
    }
  }

  def updateStatus(status:String,projectId:Long)={
    DB.withConnection{implicit connection=>
      SQL("UPDATE projects SET status={status} WHERE project_id={project_id}")
        .on(
        'project_id->projectId,
        'status->status
      ).executeUpdate()
    }
  }

  def findProjectById(projectId:Long):Option[Projects]={
    DB.withConnection{implicit connection=>
      SQL("SELECT name,description,status FROM projects WHERE project_id={project_id}")
      .on(
      'project_id->projectId
      ).as(Projects.simple.singleOpt)
    }
  }


}
