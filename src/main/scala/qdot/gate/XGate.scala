package qdot.gate

class XGate(wire: Int) extends Gate {
  val name = "x"
  val wires = List(wire)
  val params = List()
  lazy val inverse = XGate(wire)
}
