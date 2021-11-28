package examples

import org.junit.Test
import qdot.circuit.Circuit
import qdot.gate.{CNOT, Hadamard, Measurement, Qubit}

class GHZState {

  @Test def buildGHZState: Unit = {
    val qubits = Qubit.listOf(3)
    val init = Circuit[3](List(), qubits)
    val superpos = init.add(Hadamard(qubits(0)))
    val ghzState = superpos.add(CNOT(qubits(0), qubits(1))).add(CNOT(qubits(1),qubits(2)))
    val ghzStateMeasured = ghzState.measureAll()
  }

}
