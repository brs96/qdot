package examples

import org.junit.Test
import qdot.circuit.Circuit
import qdot.gate.{CNOT, Hadamard, Measurement}

class GHZState {

  @Test def buildGHZState: Unit = {
    val init = new Circuit[3](List())
    val superpos = init.add(Hadamard[3](0))
    val ghzState = superpos.add(CNOT[3](0, 1)).add(CNOT[3](1,2))
    val ghzStateMeasured = ghzState.add(Measurement(0)).add(Measurement(1)).add(Measurement(2))
    ghzStateMeasured.opSeq.map(print(_))
  }

}
