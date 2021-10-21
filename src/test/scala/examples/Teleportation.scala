package examples

import org.junit.Test
import qdot.circuit.Circuit
import qdot.gate.{CNOT, CRegControlledOp, CZ, Hadamard, Measurement, Qubit, UGate, XGate, ZGate}

class Teleportation {

  @Test def buildQFTState: Unit = {
    val circuit = Circuit[3](List(), Qubit.listOf(3))
    val init = circuit.add(UGate(0,0,0,circuit.qubits(0))).add(Hadamard(circuit.qubits(1))).add(CNOT(circuit.qubits(1),circuit.qubits(2)))
    val aliceEntangle = init.add(CNOT(circuit.qubits(0),circuit.qubits(1))).add(Hadamard(circuit.qubits(0)))
    val teleport = aliceEntangle.add(Measurement(circuit.qubits(0))).add(Measurement(circuit.qubits(1)))
      .add(CRegControlledOp(1, 1, XGate(circuit.qubits(2)))).add(CRegControlledOp(0, 1, ZGate(circuit.qubits(2))))
    val BobResult = teleport.add(Measurement(circuit.qubits(2)))
  }
}
