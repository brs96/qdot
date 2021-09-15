package examples

import org.junit.Test
import qdot.algorithm.BernsteinVazirani
import qdot.compiler.QASMCompiler

class BernsteinVazirani {

  @Test def buildBVCircuit: Unit = {
    //example 8 bit string
    val bvcircuit = BernsteinVazirani(List(1, 1, 1, 0, 1, 0, 0, 0), 8)
    val bitString = bvcircuit.add(Measurement(0)).add(Measurement(1)).add(Measurement(2)).add(Measurement(3))
      .add(Measurement(4)).add(Measurement(5)).add(Measurement(6)).add(Measurement(7))
  }
}
