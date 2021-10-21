package qdot.gate

//PhaseGate
class RZGate(phi: Double, wire: Qubit) extends NativeGate {
  val name = "rz"
  val wires = List(wire)
  val params = List(phi)
  lazy val inverse = RZGate(-phi, wire)
}
