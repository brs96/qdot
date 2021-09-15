package qdot.gate

class Hadamard(wire: Int) extends Gate {
  val name = "h"
  val wires = List(wire)
  val params = List.empty[Int]
}
