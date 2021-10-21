package examples

import org.junit.Test
import qdot.gate.{Qubit, Unitary, CPhase, XGate}
import qdot.algorithm.QPE
import scala.math.Pi

class QPEState {

  @Test def buildQFTState: Unit = {
    def controlledT(c: Qubit, t: List[Qubit]): Unitary = CPhase(Pi/4, c, t.head)
    val qpe = QPE(Qubit.listOf(3), controlledT, XGate(Qubit.listOf(1).head))
  }

}
