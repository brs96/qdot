//package qdot
import qdot.algorithm.{BernsteinVazirani, QFT}
import qdot.backend.IBMQBackend
import qdot.circuit.Circuit
import qdot.compiler.{QASMCompiler, compilerMacros}
import qdot.gate.*

import java.util.Properties
import javax.script.ScriptEngineManager
import java.io.{BufferedReader, InputStreamReader}

@main def hello: Unit = {

  val bvcircuit = BernsteinVazirani(List(1,1,1,0,1,0,0,0), 8)
  val bitString = bvcircuit.add(Measurement(0)).add(Measurement(1)).add(Measurement(2)).add(Measurement(3)).add(Measurement(4)).add(Measurement(5)).add(Measurement(6)).add(Measurement(7))
  QASMCompiler.toQASM(bitString, 8)

  val ibmq = new IBMQBackend
  val apiStr = "123"
  val qasmFile = "src/main/qasm/data.qasm"
  val result = ibmq.submitQASMToIBMQ(apiStr, qasmFile)
  print(result)

}
