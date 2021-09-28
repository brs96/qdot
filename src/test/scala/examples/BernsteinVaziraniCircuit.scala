package examples

import org.junit.Test
import qdot.algorithm.BernsteinVazirani
import qdot.compiler.QASMCompiler
import qdot.gate.Measurement

class BernsteinVaziraniCircuit {

  @Test def buildBVCircuit: Unit = {
    //example 8 bit string
    //https://arxiv.org/pdf/quant-ph/0012114.pdf
    val bvcircuit = BernsteinVazirani(List(1, 1, 1, 0, 1, 0, 0, 0), 8)
    val bitString = bvcircuit.add(Measurement(0)).add(Measurement(1)).add(Measurement(2)).add(Measurement(3))
      .add(Measurement(4)).add(Measurement(5)).add(Measurement(6)).add(Measurement(7))
  }
}
