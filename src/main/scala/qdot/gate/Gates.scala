package qdot.gate

import cats.arrow._

trait Qubit[-N <: Int] {

  def hadamard(wire: Int): Qubit[N]

  def cnot(wire1: Int, wire2: Int): Qubit[N]

}

class SimulatedQubit[-N <: Int](m: List[List[BigDecimal]]) extends Qubit[N] {

  val matrix: List[List[BigDecimal]] = m

  override def hadamard(wire: Int): SimulatedQubit[N] = SimulatedQubit(matrix.map(list => list.map(decimal => decimal + 1)))

  override def cnot(wire1: Int, wire2: Int): SimulatedQubit[N] = SimulatedQubit(matrix.map(list => list.map(decimal => decimal + 1)))

}

class QASMSet[-N <: Int](qasmStr: String) extends Qubit[N] {

  val QASMStr: String = qasmStr

  override def hadamard(wire: Int): QASMSet[N] = QASMSet[N](QASMStr ++ s"h q[$wire];\n")

  override def cnot(wire1: Int, wire2: Int): QASMSet[N] = QASMSet[N](QASMStr ++ s"cnot q[$wire1] q[$wire2];\n")
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
