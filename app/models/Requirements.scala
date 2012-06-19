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

case class Requirements(projectId:Long,contactId:Long,fillBy:Date,priority:String,category:String)
object Requirements {

  def simple={
    get[Long]("project_id")~
    get[Long]("contact_id")~
    get[Date]("fill_by")~
    get[String]("priority")~
    get[String]("category")map{
      case project_id~contact_id~fill_by~priority~category=>Requirements(project_id,contact_id,fill_by,priority,category)
    }
  }

  def addRequirement(require:Requirements):Option[Long]={
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



}
