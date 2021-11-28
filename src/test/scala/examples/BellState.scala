package examples

import org.junit.Test
import qdot.circuit.Circuit
import qdot.compiler.QASMCompiler
import qdot.gate.{CNOT, Hadamard, Measurement, Qubit}

class BellState {

  @Test def buildBellState: Unit = {
    val qubits = Qubit.listOf(2)
    val init = Circuit[2](List(), qubits)
    val superpos = init.add(Hadamard(qubits(0)))
    val bellState = superpos.add(CNOT(qubits(0), qubits(1)))
    val bellStateMeasured: Circuit[2] = bellState.add(Measurement(qubits(0))).add(Measurement(qubits(1)))
  }

}
