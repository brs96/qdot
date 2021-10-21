package qdot.gate

import qdot.gate.Unitary

trait NativeGate extends Unitary {
  def inverse: NativeGate
  def decomposition: List[NativeGate] = List(this)
}
