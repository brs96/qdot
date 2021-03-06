package qdot.gate

class Hadamard(wire: Qubit) extends NativeGate {
  val name = "h"
  val wires = List(wire)
  val params = List.empty[Int]
  lazy val inverse = Hadamard(wire)
}
