/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 7:51 AM
 * To change this template use File | Settings | File Templates.
 */
package models

import java.util.Date
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class EmploymentRecords(companyName:String,start:Option[Date],end:Option[Date],designation:String,personId:Long)

object EmploymentRecords{

  def simple={
    get[String]("name")~
    get[Option[Date]]("start")~
    get[Option[Date]]("end")~
    get[String]("designation")~
    get[Long]("person_id")map{
      case name~start~end~designation~person_id=>EmploymentRecords(name,start,end,designation,person_id)
    }
  }

  def addEmploymentRecord(empRecord:EmploymentRecords):Boolean={
    DB.withConnection{implicit connection=>
      SQL("INSERT INTO employment_record(name,start,end,designation,person_id) VALUES({name},{start},{end},{designation},{person_id})")
        .on(
        'name->empRecord.companyName,
        'start->empRecord.start,
        'end->empRecord.end,
        'designation->empRecord.designation,
        'person_id->empRecord.personId
      ).execute()
    }
  }

  def getEmploymentRecord(personId:Long):Seq[EmploymentRecords]={
    DB.withConnection{implicit connection=>
      SQL("SELECT name,start,end,designation,person_id FROM employment_record WHERE person_id={person_id}")
        .on(
        'person_id->personId
      )
        .as(EmploymentRecords.simple *)
    }
  }

  //edit employment records
}
