package qdot.gate

import qdot.gate.{NativeGate, Unitary}

class CustomGate(n: String, w: List[Qubit], p: List[_], decomp: List[NativeGate]) extends Unitary {
  val name = n
  val wires = w
  val params = p
  val decomposition = decomp
  def inverse = CustomGate(n, w, p, decomp.reverse.map(ng => ng.inverse))
}