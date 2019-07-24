import java.util.zip._
import java.io._

object Zippy {

  def osgiPatch(inFile: File): File = {
    val outFile = new File("new.jar")
    val fos = new FileOutputStream(outFile)
    val zipOut = new ZipOutputStream(fos)

    val zipIn = new ZipInputStream(new FileInputStream(inFile))
    var zipInEntry = Option(zipIn.getNextEntry)

    while(zipInEntry.isDefined) {
      val fileToZip = new File("sourceFile")
      val fis = new FileInputStream(fileToZip)
      val zipOutEntry = new ZipEntry(fileToZip.getName)
      zipOut.putNextEntry(zipOutEntry)
      zipInEntry = Option(zipIn.getNextEntry)
    }

    outFile
  }
}
