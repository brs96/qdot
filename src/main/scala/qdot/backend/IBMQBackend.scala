package qdot.backend

import java.io.{BufferedReader, InputStreamReader}
import scala.collection.immutable.ListMap

class IBMQBackend {

  val runtime = Runtime.getRuntime

  val apiToken = "123"
  val qasmFile = "src/main/qasm/data.qasm"

  def submitQASMToIBMQ: String = {

    val process: Process = runtime.exec(s"python src/main/python/submitQASMToIBMQ.py ${apiToken} ${qasmFile}")
    val stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()))
    val stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()))

    val stdInputStr = LazyList.continually(stdInput.readLine()).takeWhile(_ != null).mkString("\n") ++ "\n\n"

    System.out.println("Here is the standard ERROR of the command:\n")
    val stdErrorStr = LazyList.continually(stdError.readLine()).takeWhile(_ != null).mkString("\n") ++ "\n\n"
    print(stdErrorStr)
    stdInputStr
  }

  def parseIBMQOutput(ibmqOutput: String): Map[List[Int], Int] = {
    print("The ibmqOutput Str is: " + ibmqOutput)
    val allResult = ibmqOutput.filterNot("{}' \n".toSet).split(",")
    val countMap = allResult.map(bitStrWithCount => bitStrWithCount.split(":"))
      .map(strCountPair => (strCountPair.head.split("").map(_.toInt).toList.reverse, strCountPair(1).toInt)).toMap
    ListMap(countMap.toSeq.sortWith(_._2 > _._2):_*)
  }
}
