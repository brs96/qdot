package qdot.gate

class UGate(theta: Double, phi: Double, lambda: Double, wire: Qubit) extends NativeGate {
  val name = "u3"
  val wires = List(wire)
  val params = List(theta, phi, lambda)
  lazy val inverse = UGate(-theta, -lambda, -phi, wire)
}
