package qdot.gate

import scala.compiletime.ops.int.-

class QASMSet[N <: Int](qasmStr: String) extends Qubit[N] {

  val QASMStr: String = qasmStr

  inline def hadamard(inline wire: Int): QASMSet[N] = QASMSet[N](QASMStr ++ s"h q[$wire];\n")

  def cnot(wire1: Int, wire2: Int): QASMSet[N] = QASMSet[N](QASMStr ++ s"cnot q[$wire1] q[$wire2];\n")

  def measure(wire: Int): (Int, QASMSet[N-1]) = (1, QASMSet[N-1](QASMStr ++ s"measure q[$wire];\n"))

}

object QASMSet {

  def apply[N <: Int](qasmStr: String): QASMSet[N] = {
    new QASMSet[N](qasmStr)
  }

  def init[N <: Int](n: Int): QASMSet[N] = {
    val str = s"qreg c[${n}]; \n"
    new QASMSet[N](str)
  }

}

