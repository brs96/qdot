package qdot.gate

class XGate(wire: Qubit) extends NativeGate {
  val name = "x"
  val wires = List(wire)
  val params = List()
  lazy val inverse = XGate(wire)
}
