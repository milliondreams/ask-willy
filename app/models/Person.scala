/**
 * Created by IntelliJ IDEA.
 * User: shiti
 * Date: 6/7/12
 * Time: 9:40 PM
 * To change this template use File | Settings | File Templates.
 */
package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._


case class Person(//id:Long,
                  name:String,
                  email:String,
                  designation:String,
                  gender:String,
                  experience:Double,
                  available:Boolean,
//                  shortResume:String,
//                  formalResume:Stream[String],
                  location:String,
                  employee:Boolean

                   )

object Person{

  def simple={
//    get[Long]("id")~
    get[String]("name")~
    get[String]("email")~
    get[String]("designation")~
    get[String]("gender")~
    get[Double]("experience")~
    get[Boolean]("available")~
//    get("short_resume")~
//    get("formal_resume")~
    get[String]("location")~
    get[Boolean]("employee")map{
      case name~email~designation~gender~experience~available~location~employee=>Person(name,email,designation,gender,experience,available,location,employee)
    }

  }

  def addPerson(ppl:Person):Option[Long]={
    DB.withConnection{implicit connection=>
    val id=SQL("INSERT INTO people(name,email,designation,gender,experience,available,location,employee) VALUES ({name},{email},{designation},{gender},{experience},{available},{location},{employee}) ")
        .on(
          'name->ppl.name,
          'email->ppl.email,
          'designation->ppl.designation,
          'gender->ppl.gender,
          'experience->ppl.experience,
          'available->ppl.available,
          'location->ppl.location,
          'employee->ppl.employee
        ).executeInsert()
    id
    }
  }

  def getAll:Seq[Person]={
    DB.withConnection{implicit connection=>
      SQL("SELECT * FROM people")
        .as(Person.simple *)
    }
  }

  def getAllEmployees:Seq[Person]={
    DB.withConnection{implicit connection=>
      SQL("SELECT * FROM people where employee=true")
        .as(Person.simple *)
    }
  }
  def findPerson(emailId:String):Option[Person]={
    DB.withConnection{implicit connection=>
      SQL("SELECT * FROM people where email={email}")
        .on(
      'email->emailId
      )
        .as(Person.simple.singleOpt)
    }
  }
  def findPersonById(personId:Long):Option[Person]={
    DB.withConnection{implicit connection=>
      SQL("SELECT * FROM people where id={id}")
        .on(
      'id->personId
      )
        .as(Person.simple.singleOpt)
    }
  }
  def removePersonById(personId:Long):Boolean={
    DB.withConnection{implicit connection=>
      SQL("DELETE FROM people where id={id}")
        .on(
      'id->personId
      ).execute()
    }
  }

}
