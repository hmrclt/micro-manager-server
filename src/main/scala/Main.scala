package example2

import java.io._
import org.osgi.framework._
import org.apache.felix.framework._, util.FelixConstants
import org.apache.felix.main.AutoProcessor
import collection.JavaConverters._
import org.osgi.util.tracker.ServiceTracker

object FrameworkX {

  def instance = {
    val m_fwk = new Felix(null)

    m_fwk.start
    m_fwk
  }

  def getFrameworkFactory() = {

    val resName = "META-INF/services/org.osgi.framework.launch.FrameworkFactory"
    val res: Option[String] =
      io.Source.fromResource(resName)
        .getLines
        .map(_.trim)
        .find(!_.startsWith("#"))

    res match {
      case Some(s) =>
        Class.forName(s).newInstance.asInstanceOf[FrameworkFactory]
      case None =>
        throw new Exception("Could not find framework factory.");
    }
  }

}

object Main extends App {
  val m_fwk = FrameworkX.instance
  m_fwk.waitForStop(0)


}
