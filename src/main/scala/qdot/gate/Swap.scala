package qdot.gate

class Swap(wire1: Int, wire2: Int) extends Gate {
  val name = "swap"
  val wires = List(wire1, wire2)
  val params = List()
  lazy val inverse = Swap(wire1, wire2)
}
