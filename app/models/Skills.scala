/**
 * Created by IntelliJ IDEA.
 * User: shiti
 * Date: 6/7/12
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */

package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._


case class Skills(id:Long,skillName:String)

object Skills{
  def simple={
    get[Long]("id")~
    get[String]("skillName")map{
      case id~skillName=> Skills(id,skillName)
    }
  } 
  
  def findById(id:Long):Option[Skills]={
    DB.withConnection{implicit connection=>
      SQL("SELECT * FROM skills WHERE id={id}")
      .on(
        'id->id
      ).as(Skills.simple.singleOpt)
    }
  }

  def addSkill(skillName:String):Option[Long]={
    DB.withConnection{implicit connection=>
      SQL("INSERT INTO skills(skill_name) VALUES({skill_name})")
      .on(
        'skill_name->skillName
      ).executeInsert()
      
    }

  }

  def findByWords(keyWords:String):Seq[Skills]={
    DB.withConnection{implicit connection=>
      SQL("SELECT * FROM skills WHERE skill_name LIKE '%{searchString}%'")
      .on(
        'searchString->keyWords
      ).as(Skills.simple *)
    }
  }
}
