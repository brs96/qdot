package examples

import org.junit.Test
import qdot.circuit.Circuit
import qdot.gate.{CNOT, CRegControlledOp, CZ, Hadamard, Measurement, UGate, XGate, ZGate}

class Teleportation {

  @Test def buildQFTState: Unit = {
    val qubits = new Circuit[3](List())
    val init = qubits.add(UGate(0,0,0,0)).add(Hadamard(1)).add(CNOT(1,2))
    val aliceEntangle = init.add(CNOT(0,1)).add(Hadamard(0))
    val teleport = aliceEntangle.add(Measurement(0)).add(Measurement(1))
      .add(CRegControlledOp(1, 1, XGate(2))).add(CRegControlledOp(0, 1, ZGate(2)))
    //.add(CNOT(1,2)).add(CZ(0,2))    equivalent result
    val BobResult = teleport.add(Measurement(2))
  }
}
