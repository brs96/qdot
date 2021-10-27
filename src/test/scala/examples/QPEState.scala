package examples

import org.junit.Test
import qdot.gate.{Qubit, Unitary, CPhase, XGate}
import qdot.algorithm.QPE
import scala.math.Pi

class QPEState {

  @Test def buildQFTState: Unit = {
    //theta = 1/8
    def cT1(c: Qubit, t: List[Qubit]): Unitary = CPhase(Pi/4, c, t.head)
    val qpe1 = QPE(Qubit.listOf(3), cT1, XGate(Qubit("eigenstate")))

    //theta = 1/3
    def cT2(c: Qubit, t: List[Qubit]): Unitary = CPhase(2*Pi/3, c, t.head)
    val qpe2 = QPE(Qubit.listOf(6), cT2, XGate(Qubit("eigenstate")))
  }

}
