package qdot.gate

class ZGate(wire: Qubit) extends NativeGate {
  val name = "z"
  val wires = List(wire)
  val params = List()
  lazy val inverse = ZGate(wire)
}
