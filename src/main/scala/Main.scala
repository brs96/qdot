//package qdot
import qdot.backend.IBMQBackend
import qdot.circuit.Circuit
import qdot.gate
import qdot.gate.*

import java.util.Properties
import javax.script.ScriptEngineManager
import java.io.{BufferedReader, InputStreamReader}

@main def hello: Unit = {

  val circuit1 = new Circuit[2](List())
  val superpos = circuit1.add(Hadamard[2](0))
  print(superpos.gateSeq)
  val bellState = superpos.add(CNOT[2](0, 1))
  print(bellState.gateSeq)
  //val invalid = bellState.add(CNOT[2](2,3))

//  val ibmq = new IBMQBackend
//  val apiStr = "123"
//  val qasmFile = "src/main/qasm/BellState.qasm"
//  val result = ibmq.submitQASMToIBMQ(apiStr, qasmFile)
//  print(result)

}
