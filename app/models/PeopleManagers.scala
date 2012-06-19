/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 9:30 PM
 * To change this template use File | Settings | File Templates.
 */

package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class PeopleManagers(personId:Long,managerId:Long,current:Boolean)

object PeopleManagers{

  def simple={
    get[Long]("person_id")~
    get[Long]("manager_id")~
    get[Boolean]("current")map{
      case person_id~manager_id~current=>PeopleManagers(person_id,manager_id,current)
    }
  }

  def addPersonManager(personManager:PeopleManagers):Option[Long]={
    DB.withConnection{implicit connection=>
      SQL("INSERT INTO people_managers(person_id,manager_id,current) VALUES({person_id},{manager_id},{current})")
        .on(
        'person_id->personManager.personId,
        'manager_id->personManager.managerId,
        'current->personManager.current
      ).executeInsert()
    }
  }


}
