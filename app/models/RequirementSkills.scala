/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/17/12
 * Time: 12:15 AM
 * To change this template use File | Settings | File Templates.
 */
package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class RequirementSkills(requirementId:Long,skillId:Long,skillLevel:String)

object RequirementSkills{

  def simple={
    get[Long]("requirement_id")~
    get[Long]("skill_id")~
    get[String]("skill_level")map{
      case requirement_id~skill_id~skill_level=>RequirementSkills(requirement_id,skill_id,skill_level)
    }
  }

  def addReqSkills(reqSkills:RequirementSkills):Option[Long]={
    DB.withConnection{implicit connection=>
      SQL("INSERT INTO requirements_skills(requirement_id,skill_id,skill_level) VALUES({requirement_id},{skill_id},{skill_level})")
      .on(
        'requirement_id->reqSkills.requirementId,
        'skill_id->reqSkills.skillId,
        'skill_level->reqSkills.skillLevel
      ).executeInsert()
    }
  }

}
