//package qdot
import qdot.backend.IBMQBackend
import qdot.circuit.Circuit
import qdot.compiler.QASMCompiler
import qdot.gate
import qdot.gate.*

import java.util.Properties
import javax.script.ScriptEngineManager
import java.io.{BufferedReader, InputStreamReader}

@main def hello: Unit = {

  val circuit1 = new Circuit[2](List())
  val superpos = circuit1.add(Hadamard[2](0))
  val bellState = superpos.add(CNOT[2](0, 1))
  //val invalid = bellState.add(CNOT[2](2,3))
  QASMCompiler.toQASM(bellState)

  val ibmq = new IBMQBackend
  val apiStr = "123"
  val qasmFile = "src/main/qasm/data.qasm"
  val result = ibmq.submitQASMToIBMQ(apiStr, qasmFile)
  print(result)

}
