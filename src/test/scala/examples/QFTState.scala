package examples

import org.junit.Test
import qdot.algorithm.QFT
import qdot.circuit.Circuit
import qdot.gate.{Measurement}

class QFTState {

  @Test def buildQFTState: Unit = {
    val circuit2 = new Circuit[4](List())
    val withQFT = circuit2.add(QFT[4](4))
    val qftMeasured = withQFT.add(Measurement(0)).add(Measurement(1)).add(Measurement(2)).add(Measurement(3))
  }

}
