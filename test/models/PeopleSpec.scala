/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
package models

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import utils.MyHelpers

import org.h2.tools.Server;

class PeopleSpec extends Specification{
  val person:Person = Person(
    name = "test",
    email="test@test.com",
    designation = "Dev Engineer",
    gender = "male",
    experience = 1.0,
    available = true,
    location = "hyderabad",
    employee = true
  )
  "Person person " should {

    "have a name as test" in {
       person.name must beEqualTo("test")
    }

    "be saved in a db" in {
      // start the TCP Server


      running(FakeApplication(additionalConfiguration = inMemoryDatabase())){
        def server:Server  = Server.createTcpServer("-tcpAllowOthers").start();
        MyHelpers.evolutionFor("default")

         def result = Person.addPerson(person) must beEqualTo(1)

        server.stop();
        // stop the TCP Server

        result
      }

    }
  }
}
