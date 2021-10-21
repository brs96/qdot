package examples

import org.junit.Test
import qdot.circuit.Circuit
import qdot.gate.{CNOT, Hadamard, Measurement, Qubit}

class GHZState {

  @Test def buildGHZState: Unit = {
    val init = Circuit[3](List(), Qubit.listOf(3))
    val superpos = init.add(Hadamard(init.qubits(0)))
    val ghzState = superpos.add(CNOT(init.qubits(0), init.qubits(1))).add(CNOT(init.qubits(1),init.qubits(2)))
    val ghzStateMeasured = ghzState.measureAll()
  }

}
