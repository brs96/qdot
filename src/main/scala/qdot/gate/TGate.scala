package qdot.gate

import scala.math.Pi

//PhaseGate(pi/4) = RZGate(pi/4)
class TGate(wire: Qubit) extends RZGate(Pi/4, wire) {
  override val name = "t"
  override val params = List()
}
