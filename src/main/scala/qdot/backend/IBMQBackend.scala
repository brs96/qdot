package qdot.backend

import java.io.{BufferedReader, InputStreamReader}

class IBMQBackend {

  val runtime = Runtime.getRuntime

  def submitQASMToIBMQ(apiToken: String, qasmFile: String): String = {

    val process: Process = runtime.exec(s"python src/main/python/submitQASMToIBMQ.py ${apiToken} ${qasmFile}")
    val stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()))
    val stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()))

    val stdInputStr = LazyList.continually(stdInput.readLine()).takeWhile(_ != null).mkString("\n") ++ "\n\n"

    System.out.println("Here is the standard ERROR of the command:\n")
    val stdErrorStr = LazyList.continually(stdError.readLine()).takeWhile(_ != null).mkString("\n") ++ "\n\n"
    print(stdErrorStr)
    stdInputStr
  }
}
