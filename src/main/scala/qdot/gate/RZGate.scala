package qdot.gate

//PhaseGate
class RZGate(phi: Double, wire: Int) extends Gate {
  val name = "rz"
  val wires = List(wire)
  val params = List(phi)
  lazy val inverse = RZGate(-phi, wire)
}
