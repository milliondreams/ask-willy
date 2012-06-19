/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
package models

import anorm.SqlParser._
import anorm._
import play.api.db._
import play.api.Play.current

case class Managers(personId:Long,projectId:Long,current:Boolean)

object Managers{

  def simple={
    get[Long]("person_id")~
    get[Long]("project_id")~
    get[Boolean]("current")map{
      case person_id~project_id~current=>Managers(person_id,project_id,current)
    }
  }

  def addToManagers(manager:Managers)={
    DB.withConnection{implicit connection=>
      SQL("INSERT INTO managers(person_id,project_id,current) VALUES ({person_id},{project_id},{current}")
        .on(
        'person_id->manager.personId,
        'project_id->manager.projectId,
        'current->manager.current
      ).execute()
    }
  }

  def toggleCurrent(manager:Managers)={
    DB.withConnection{implicit connection=>
      SQL("UPDATE managers SET current={current} WHERE person_id={person_id} AND project_id={project_id}")
        .on(
        'person_id->manager.personId,
        'project_id->manager.projectId,
        'current->(!manager.current)
      ).execute()
    }
  }

  def getAllManagers:Seq[Managers]={
    DB.withConnection{implicit connection=>
      SQL("SELECT person_id,project_id,current FROM managers")
        .as(Managers.simple *)
    }
  }

  def getCurrentManagers:Seq[Managers]={
    DB.withConnection{implicit connection=>
      SQL("SELECT person_id,project_id,current FROM managers WHERE current=true")
        .as(Managers.simple *)
    }
  }

}
