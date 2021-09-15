package qdot.gate

class ZGate(wire: Int) extends Gate {
  val name = "z"
  val wires = List(wire)
  val params = List()
}
