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

case class Project(name:String,description:String,status:String)

object Project{

  def simple={
    get[String]("name")~
    get[String]("description")~
    get[String]("status")map{
      case name~description~status=>Project(name,description,status)
    }
  }

  def addProject(project:Project):Option[Long]={
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

  def findProjectById(projectId:Long):Option[Project]={
    DB.withConnection{implicit connection=>
      SQL("SELECT name,description,status FROM projects WHERE project_id={project_id}")
      .on(
      'project_id->projectId
      ).as(Project.simple.singleOpt)
    }
  }

  def findProjectByName(projectName:String):Option[Project]={
    DB.withConnection{implicit connection=>
      SQL("SELECT name,description,status FROM projects WHERE name={name}")
        .on(
        'name->projectName
      ).as(Project.simple.singleOpt)
    }
  }

  def getAll:Seq[Project]={
    DB.withConnection{implicit connection=>
      SQL("SELECT name,description,status FROM projects")
      .as(Project.simple *)
    }
  }

  def getByStatus(status:String):Seq[Project]={
    DB.withConnection{implicit connection=>
      SQL("SELECT name,description,status FROM projects WHERE status={status}")
        .on(
        'status->status
      )
        .as(Project.simple *)
    }
  }

  def removeProjectById(projectId:Long):Boolean={
    DB.withConnection{implicit connection=>
      SQL("DELETE FROM projects WHERE id={id}")
      .on(
        'id->projectId
      ).execute()
    }
  }


}
