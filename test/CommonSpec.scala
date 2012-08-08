/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 7/29/12
 * Time: 6:50 PM
 * To change this template use File | Settings | File Templates.
 */

package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import play.api.test.FakeRequest
import play.api.libs.json.JsValue


class CommonSpec extends Specification {
    "respond to the postCommon Action" in{
      running(FakeApplication()) {
      val result=routeAndCall(FakeRequest(POST,"/api/persons")).get
        status(result) must equalTo(OK)
      }
    }
}
