package examples

import org.junit.Test
import qdot.circuit.Circuit
import qdot.gate.{CNOT, Hadamard, UGate, Measurement, CZ}

class Teleportation {

  @Test def buildQFTState: Unit = {
    val qubits = new Circuit[3](List())
    val init = qubits.add(UGate(0,0,0,0)).add(Hadamard[3](1)).add(CNOT[3](1,2))
    val aliceEntangle = init.add(CNOT[3](0,1)).add(Hadamard[3](0))
    val teleport = aliceEntangle.add(Measurement(0)).add(Measurement(1)).add(CNOT[3](1,2)).add(CZ(0,2))
    val BobResult = teleport.add(Measurement(2))
  }
}
