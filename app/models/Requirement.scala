/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
package models

import java.util.Date
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Requirement(projectId:Long,contactId:Long,fillBy:Date,priority:String,category:String)
object Requirement {

  def simple={
    get[Long]("project_id")~
    get[Long]("contact_id")~
    get[Date]("fill_by")~
    get[String]("priority")~
    get[String]("category")map{
      case project_id~contact_id~fill_by~priority~category=>Requirement(project_id,contact_id,fill_by,priority,category)
    }
  }

  def addRequirement(require:Requirement):Option[Long]={
    DB.withConnection{implicit connection=>
      SQL("INSERT INTO requirements(project_id,contact_id,fill_by,priority,category) VALUES({project_id},{contact_id},{fill_by},{priority},{category})")
      .on(
      'project_id->require.projectId,
      'contact_id->require.contactId,
      'fill_by->require.fillBy,
      'priority->require.priority,
      'category->require.category
      ).executeInsert()
    }
  }

  def findById(requirementId:Long):Option[Requirement]={
    DB.withConnection{implicit connection=>
      SQL("SELECT project_id,contact_id,fill_by,priority,category FROM requirements WHERE id={id}")
      .on('id->requirementId)
      .as(Requirement.simple.singleOpt)
    }
  }

  def getRequirements:Seq[Requirement]={
    DB.withConnection{implicit connection=>
      SQL("SELECT project_id,contact_id,fill_by,priority,category FROM requirements")
      .as(Requirement.simple *)
    }
  }

  def getReqByPriority(priority:String) :Seq[Requirement]={
    DB.withConnection{implicit connection=>
      SQL("SELECT project_id,contact_id,fill_by,priority,category FROM requirements WHERE priority={priority}")
        .on('priority->priority)
        .as(Requirement.simple *)
    }
  }

  def getReqByCategory(category:String) :Seq[Requirement]={
    DB.withConnection{implicit connection=>
      SQL("SELECT project_id,contact_id,fill_by,priority,category FROM requirements WHERE category={category}")
        .on('category->category)
        .as(Requirement.simple *)
    }
  }

  def removeRequirement(requirementId:Long):Boolean={
    DB.withConnection{implicit connection=>
      SQL("DELETE FROM requirements WHERE id={id}")
    .on(
      'id->requirementId
      ).execute()
    }
  }

}
