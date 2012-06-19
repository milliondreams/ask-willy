/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 8:30 AM
 * To change this template use File | Settings | File Templates.
 */

package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class QualificationRecords(personId:Long,qualification:String,institute:String,started:Int,completed:Int)

object QualificationRecords{

  def simple={
    get[Long]("person_id")~
    get[String]("qualification")~
    get[String]("institute")~
    get[Int]("started")~
    get[Int]("completed")map{
      case person_id~qualification~institute~started~completed=>QualificationRecords(person_id,qualification,institute,started,completed)
    }
  }

  def addQualification(qualificationRecord:QualificationRecords):Option[Long]={
    DB.withConnection{implicit connection=>
      SQL("INSERT into qualifications_records(person_id,qualification,institute,started,completed) VALUES({person_id},{qualification},{institute},{started},{completed})")
        .on(
        'person_id->qualificationRecord.personId,
        'qualification->qualificationRecord.qualification,
        'institute->qualificationRecord.institute,
        'started->qualificationRecord.started,
        'completed->qualificationRecord.completed
      ).executeInsert()
    }
  }

  def getQualification(personId:Long):Seq[QualificationRecords]={
    DB.withConnection{implicit connection=>
      SQL("SELECT person_id,qualification,institute,started,completed FROM qualifications_records WHERE person_id={person_id}")
        .on(
        'person_id->personId
      ).as(QualificationRecords.simple *)
    }
  }

  //edit qualification
}
