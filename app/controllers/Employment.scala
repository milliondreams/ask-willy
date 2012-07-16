/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 7/15/12
 * Time: 11:37 PM
 * To change this template use File | Settings | File Templates.
 */
package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.{Json, JsValue}
import models.EmploymentRecords
import changeFormat.JsonDateFormatter
import com.codahale.jerkson.Json._


object Employment extends Controller{

  def postEmpRec=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{employment=>

      val prevEmpId=EmploymentRecords.addEmploymentRecord(EmploymentRecords((employment\"company").toString(),JsonDateFormatter.DateFormat.reads(employment\"start"),JsonDateFormatter.DateFormat.reads(employment\"end"),(employment\"designation").toString(),(employment\"personId").as[Long]));
      Ok(Json.stringify(
        Json.toJson(Map("id" -> prevEmpId))
      ))}.getOrElse{
      BadRequest("Missing or Invalid Parameters")
    }
  }

  def getEmploymentRec(personId:Long)=Action{
    val empRec=EmploymentRecords.getEmploymentRecord(personId)
    Ok(generate(empRec))
  }
}
