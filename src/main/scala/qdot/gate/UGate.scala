package qdot.gate
//import org.apache.commons.math3.complex._

class UGate(theta: Double, phi: Double, lambda: Double, wire: Int) extends Gate {
  val name = "u3"
  val wires = List(wire)
  val params = List(theta, phi, lambda)
  lazy val inverse = UGate(-theta, -lambda, -phi, wire)
}
