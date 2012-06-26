/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/23/12
 * Time: 11:12 PM
 * To change this template use File | Settings | File Templates.
 */

package changeFormat

import play.api.libs.json.{JsValue, Format,Json}
import java.util.Date
import java.text.SimpleDateFormat

object JsonDateFormatter{
implicit object DateFormat extends Format[Option[Date]] {

    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    def writes(date: Option[Date]): JsValue = {
      Json.toJson(
        date.map(
          date => dateFormat.format(date)
        ).getOrElse(
          ""
        )
      )
    }

    def reads(j: JsValue): Option[Date] = {
      try {
        Some(dateFormat.parse(j.as[String]))
      } catch {
        case e => None
      }
    }

}
}
