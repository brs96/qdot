package examples

import org.junit.Test
import qdot.algorithm.BernsteinVazirani
import qdot.compiler.QASMCompiler
import qdot.gate.{Measurement, Qubit}

class BernsteinVaziraniCircuit {

  @Test def buildBVCircuit: Unit = {
    //example 8 bit string
    //https://arxiv.org/pdf/quant-ph/0012114.pdf
    val bvcircuit = BernsteinVazirani(List(1, 1, 1, 0, 1, 0, 0, 0), Qubit.listOf(8))
  }
}
