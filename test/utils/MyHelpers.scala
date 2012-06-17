package utils

import play.api.db.DBPlugin
import play.api.db.evolutions.Evolutions
import play.api.Play

/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/16/12
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */

class MyHelpers {
  def evolutionFor(dbName: String, path: java.io.File = new java.io.File(".")) {
    Play.current.plugin[DBPlugin].foreach {
      db =>
        val script = Evolutions.evolutionScript(db.api, path, db.getClass.getClassLoader, dbName)
        Evolutions.applyScript(db.api, dbName, script)
    }
  }
}
