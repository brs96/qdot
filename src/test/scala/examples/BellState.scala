package examples

import org.junit.Test
import qdot.circuit.Circuit
import qdot.compiler.QASMCompiler
import qdot.gate.{CNOT, Hadamard, Measurement, Qubit}

class BellState {

  @Test def buildBellState: Unit = {
    val init = Circuit[2](List(), Qubit.listOf(2))
    val superpos = init.add(Hadamard(init.qubits(0)))
    val bellState = superpos.add(CNOT(init.qubits(0), init.qubits(1)))
    val bellStateMeasured: Circuit[2] = bellState.add(Measurement(init.qubits(0))).add(Measurement(init.qubits(1)))
  }

}
