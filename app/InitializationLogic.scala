// File: app/MyInitialization.scala
import javax.inject.Inject
import play.api._
import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}

class InitializationLogic @Inject() () {
  println("Executing custom logic before application starts")
  createFiles()
  def createFiles() = {
    val directory = new File("Files" +
      "")
    directory.mkdir()
    try {
      val file1 = createFile("file.txt", "A simple text file")
      val file2 = createFile("file.json", "{" +
    		"\"description\" : \"A simple json file \"}" 
 			)
      val file3 = createFile("file.doc", "A simple document file")
      println("Created files successfully")
    } catch {
      case e: Exception =>
        println("An Error Occurred!!!")
    }
  }
  def createFile(name: String, content: String): File = {
    val newFile = new File("Files/" + name)
    val writer = new PrintWriter(newFile);
    writer.write(content)
    writer.flush()
		writer.close()
    newFile
  }
	
}
object InitializationLogic {
	def deleteFiles() = {
		val createdFilePaths = List("Files/file.doc", "Files/file.txt", "Files/file.json")
		createdFilePaths.foreach { filePath =>
			try {
				val file = Paths.get(filePath)
				if (Files.exists(file)) {
					Files.delete(file)
					println(s"Deleted file: $filePath")
				} else {
					println(s"File not found: $filePath")
				}
			} catch {
				case e: Exception =>
					println(s"Error deleting file: $filePath", e)
			}
		}
	}
}