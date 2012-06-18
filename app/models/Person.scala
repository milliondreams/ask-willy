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


case class People(id:Long,
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

object People{

  def simple={
    get[Long]("id")~
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
      case id~name~email~designation~gender~experience~available~location~employee=>People(id,name,email,designation,gender,experience,available,location,employee)
    }

  }

  def addPerson(ppl:People):Option[Long]={
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

  //edit fields

  def getAll:Seq[People]={
    DB.withConnection{implicit connection=>
      SQL("SELECT * FROM people")
        .as(People.simple *)
    }
  }

  def getAllEmployees:Seq[People]={
    DB.withConnection{implicit connection=>
      SQL("SELECT * FROM people where employee=true")
        .as(People.simple *)
    }
  }
}
