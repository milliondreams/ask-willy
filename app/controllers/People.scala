/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/17/12
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
package controllers

import play.api.mvc._
import play.api._
import libs.json.JsValue
import play.api.libs.json.Json

import java.util.Date

import com.codahale.jerkson.Json._

import models._


object People extends Controller{

  /* POST */
  def postPerson=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{person=>

      val personId=Person.addPerson(Person((person\"name").as[String],(person\"email").as[String],(person\"designation").as[String],(person\"gender").as[String],(person\"experience").as[Double],(person\"available").as[Boolean],(person\"location").as[String],(person\"employee").as[Boolean]))
      Ok(Json.stringify(
          Json.toJson(Map("id" -> personId))
        ))}.getOrElse{
        BadRequest("failed to add person")

    }
  }
  /* POST for skill */
  def postSkill(personId:Long)=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{skillSet=>

      val skillSetId=PeopleSkills.addPeopleSkills(PeopleSkills(personId,(skillSet\"skillId").as[Long],(skillSet\"level").toString()))
      Ok(Json.stringify(
          Json.toJson(Map("skillRecordId" -> skillSetId))
        ))}.getOrElse{
        BadRequest("Missing or Invalid Parameters")

    }
  }

  /* POST for qualification */
  def postQualification(personId:Long)=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{education=>

      val qualificationId=QualificationRecords.addQualification(QualificationRecords(personId,(education\"qualification").toString,(education\"institute").toString,(education\"started").as[Int],(education\"completed").as[Int]));
      Ok(Json.stringify(
        Json.toJson(Map("qualificationRecordId" -> qualificationId))
      ))}.getOrElse{
      BadRequest("Missing or Invalid Parameters")

    }
  }

  /* POST for previous employment record */

/*  No Json deserializer found for type java.util.Date. Try to implement an implicit Reads or Format for this type.*/

  def postEmployment(personId:Long)=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{employment=>

      val prevEmpId=EmploymentRecords.addEmploymentRecord(EmploymentRecords((employment\"company").toString(),(employment\"start").as[Date],(employment\"end").as[Date],(employment\"designation").toString(),personId));
      Ok(Json.stringify(
        Json.toJson(Map("EmploymentRecordId" -> prevEmpId))
      ))}.getOrElse{
      BadRequest("Missing or Invalid Parameters")

    }
  }

  /* POST for record of managers */
  def postManagerDetails(personId:Long)=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{manager=>

      val personManagerId=PeopleManagers.addPersonManager(PeopleManagers(personId,(manager\"managerId").as[Long],(manager\"current").as[Boolean]));
      Ok(Json.stringify(
        Json.toJson(Map("PersonManagerId" -> personManagerId))
      ))}.getOrElse{
      BadRequest("Missing or Invalid Parameters")

    }
  }



  /* GET */
  def getAllPeople=Action{
    val people=Person.getAll
    Ok(generate(people))
  }

  /* GET */
  def getAllEmployees=Action{
    val people=Person.getAllEmployees
    Ok(generate(people))
  }

  /* GET */
  def getPersonByEmail(email:String)=Action{
      val person=Person.findPerson(email)
      Ok(generate(person))
  }


  /* GET */
  def getPersonById(id:Long)=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{person=>
      Person.findPersonById((person\"id").as[Long])
      Ok("get"+person.toString())
    }.getOrElse{
      BadRequest("No person with given id")
    }
  }

  /* DELETE */
  def deletePerson(personId:Long)=Action(parse.json){request=>
      val removed=Person.findPersonById(personId)
      if (Person.removePersonById(personId))
        Ok(Json.toJson(generate(removed)))
      else Ok("failed to remove person")

  }



}
