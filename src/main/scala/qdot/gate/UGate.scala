package qdot.gate
//import org.apache.commons.math3.complex._

class UGate(theta: BigDecimal, phi: BigDecimal, lambda: BigDecimal, wire: Int) extends Gate {
  val name = "u3"
  val wires = List(wire)
  val params = List(theta, phi, lambda)
}
