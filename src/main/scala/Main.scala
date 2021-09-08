//package qdot
import qdot.algorithm.QFT
import qdot.backend.IBMQBackend
import qdot.circuit.Circuit
import qdot.compiler.{QASMCompiler, compilerMacros}
import qdot.gate.*

import java.util.Properties
import javax.script.ScriptEngineManager
import java.io.{BufferedReader, InputStreamReader}

@main def hello: Unit = {

  val circuit1 = new Circuit[2](List())
  val superpos = circuit1.add(Hadamard[2](0))
  val bellState = superpos.add(CNOT[2](0, 1))
  val bellStateMeasured = bellState.add(Measurement(0)).add(Measurement(1))
  //val invalid = bellState.add(CNOT[2](2,3))

  val circuit2 = new Circuit[4](List())
  val withQFT = circuit2.add(QFT[4](4))
  val qftMeasured = withQFT.add(Measurement(0)).add(Measurement(1)).add(Measurement(2)).add(Measurement(3))
  QASMCompiler.toQASM(qftMeasured, 4)

  val ibmq = new IBMQBackend
  val apiStr = "123"
  val qasmFile = "src/main/qasm/data.qasm"
  val result = ibmq.submitQASMToIBMQ(apiStr, qasmFile)
  print(result)

}
