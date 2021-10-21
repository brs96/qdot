package examples

import org.junit.Test
import qdot.algorithm.QFT
import qdot.circuit.Circuit
import qdot.gate.{Measurement, Qubit}

class QFTState {

  @Test def buildQFTState: Unit = {
    val qft = QFT[3](Qubit.listOf(3))
    val qftMeasured = qft.measureAll()
  }

}
