/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 12:20 AM
 * To change this template use File | Settings | File Templates.
 */

package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._




case class PeopleSkills(personId:Long,skillId:Long,level:String)

object PeopleSkills{

  def simple={
    get[Long]("person_id")~
    get[Long]("skill_id")~
    get[String]("level")map{
      case person_id~skill_id~level=> PeopleSkills(person_id,skill_id,level)
    }
  }

  def addPeopleSkills(pplSkills:PeopleSkills):Option[Long]={
    DB.withConnection{implicit connection=>
      SQL("INSERT INTO people_skills(person_id,skill_id,level) VALUES({person_id},{skill_id},{level})")
      .on(
      'person_id->pplSkills.personId,
      'skill_id->pplSkills.skillId,
      'level->pplSkills.level
      ).executeInsert()
    }
  }

  def getSkills(personId:Long):Seq[PeopleSkills]={
    DB.withConnection{implicit connection=>
      SQL("SELECT skill_id,level FROM people_skills WHERE person_id={person_id}")
        .on(
        'person_id->personId
      ).as(PeopleSkills.simple *)
    }
  }

  def updateLevel(pplSkills:PeopleSkills)={
    DB.withConnection{implicit connection=>
      SQL("UPDATE people_skills SET level={level} WHERE person_id={person_id} and skill_id={skill_id}")
        .on(
        'person_id->pplSkills.personId,
        'skill_id->pplSkills.skillId,
        'level->pplSkills.level
      ).executeUpdate()
    }
  }

}
