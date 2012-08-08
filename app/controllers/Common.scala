/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 7/29/12
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */

package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.JsValue
import java.lang.reflect.Field

object Common extends Controller{

  def getObject(className:String, params:JsValue) = {
    val ModuleFieldName = "MODULE$"
    val domainClass = Class.forName("com.tingendab.Person")
    val companionClass = Class.forName(domainClass.getName + "$")
    val companionObject = companionClass.getField(ModuleFieldName).get(null)
    val args:Array[ScalaObject]=null
    val fieldNames= domainClass.getDeclaredFields.map{field:Field=>
      val name=field.getName
//      val fieldType=field.getClass
      val value=(params\name).as[String]
      val i=args.length
      args.update(i,value)
    }
    val paramTypes: Array[Class[_]] =args.map{_.getClass}
    val applyMethod = companionClass.getMethod("apply", paramTypes: _*)
    val obj= applyMethod.invoke(companionObject, args: _*).asInstanceOf[Product]
  }


  def postCommon(extension:String)=Action(parse.json){request=>
    (request.body).asOpt[JsValue].map{params=>
      val modelName=extension.substring(0,extension.length()-1)
      val model=getObject(modelName.capitalize,params)
      Ok("the model is "+model)
    }.getOrElse{
      BadRequest("invalid")
    }

  }

}
