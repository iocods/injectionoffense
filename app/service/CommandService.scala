package service

import javax.inject._
import java.io.File
import java.io._
import scala.sys.process._

class CommandService {

  def fileList(): List[String] = {
    var fileNames: List[String] = List("")
    val file = new File("Files")
    if(file.isDirectory()){
      val files = file.listFiles();
      for(f <- files){
        fileNames = f.getName() :: fileNames
      }
    }
    fileNames
  }
  def processCommand(filename: String): String = {
    var result = ""
      val osName = System.getProperty("os.name")
    try{
      if(osName.contains("dows")){
        val fileToDelete = s"Files\\${filename}"
        result = s"cmd /c del $fileToDelete"
        println(s"Executing Command $result")
        result = result.!!
      }
      else {
        val fileToDelete = s"Files\\${filename}"
        result = s"sh -c rm $fileToDelete"
        println(s"Executing Command $result")
        result = result.!!
      } 
      // Return the resultcatch {
    } catch{
        case ex: Exception =>
          // Handle exceptions (e.g., IOException)
          result = s"Error executing command: ${ex.getMessage}"
    }
    println(s"Result : $result") 
    result
  }
}