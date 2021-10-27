package examples

import org.junit.Test
import qdot.algorithm.QAOA
import qdot.backend.IBMQBackend

class QAOAMaxCut {

  val ibmq = new IBMQBackend

  @Test def buildQFTState: Unit = {
    val exampleGraph: List[List[Boolean]] = List(List(false, true, false, true), List(true, false, true, false), List(false, true, false, true), List(true, false, true, false))

    val exampleExp = new QAOA(exampleGraph, ibmq)
    val opt = exampleExp.optimize(List(2.0), List(2.0))
    print(opt)
    print("\n")
    val optimumRUn = exampleExp.executeQAOACircuit(opt._1, opt._2)
    print(optimumRUn)
  }

}
