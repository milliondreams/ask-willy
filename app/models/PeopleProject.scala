/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 8:55 AM
 * To change this template use File | Settings | File Templates.
 */

package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class PeopleProject(personId:Long,projectId:Long,current:Boolean)
object PeopleProject{

  def simple={
    get[Long]("person_id")~
    get[Long]("project_id")~
    get[Boolean]("current")map{
      case person_id~project_id~current=>PeopleProject(person_id,project_id,current)
    }
  }

  def addProject(pplProject:PeopleProject):Boolean={
    DB.withConnection{implicit connection=>
      SQL("INSERT INTO people_project(person_id,project_id,current) VALUES ({person_id},{project_id},{current}")
        .on(
        'person_id->pplProject.personId,
        'project_id->pplProject.projectId,
        'current->pplProject.current
      ).execute()
    }
  }

  def getProjects(personId:Long):Seq[PeopleProject]={
    DB.withConnection{implicit connection=>
      SQL("SELECT person_id,project_id,current FROM people_project WHERE person_id={person_id}")
        .on(
        'person_id->personId
      ).as(PeopleProject.simple *)
    }
  }

  def toggleCurrent(pplProject:PeopleProject)={
    DB.withConnection{implicit connection=>
      SQL("UPDATE people_project SET current={current} WHERE person_id={person_id} AND project_id={project_id}")
        .on(
        'person_id->pplProject.personId,
        'project_id->pplProject.projectId,
        'current->(!pplProject.current)
      ).execute()
    }
  }

}
