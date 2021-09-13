package examples

import org.junit.Test
import qdot.circuit.Circuit
import qdot.compiler.QASMCompiler
import qdot.gate.{CNOT, Hadamard, Measurement}

class BellState {

  @Test def buildBellState: Unit = {
    val init = new Circuit[2](List())
    val superpos = init.add(Hadamard[2](0))
    val bellState = superpos.add(CNOT[2](0, 1))
    val bellStateMeasured = bellState.add(Measurement(0)).add(Measurement(1))
  }

}
