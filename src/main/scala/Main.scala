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

  val qubits = new Circuit[3](List())
  val init = qubits.add(UGate(0,0,0,0)).add(Hadamard[3](1)).add(CNOT[3](1,2))
  val aliceEntangle = init.add(CNOT[3](0,1)).add(Hadamard[3](0))
  val teleport = aliceEntangle.add(Measurement(0)).add(Measurement(1)).add(CNOT[3](1,2)).add(CZ(0,2))
  val BobResult = teleport.add(Measurement(2))
  QASMCompiler.toQASM(BobResult, 3)

  val ibmq = new IBMQBackend
  val apiStr = "123"
  val qasmFile = "src/main/qasm/data.qasm"
  val result = ibmq.submitQASMToIBMQ(apiStr, qasmFile)
  print(result)

}
