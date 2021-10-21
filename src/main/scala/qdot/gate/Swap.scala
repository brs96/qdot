package qdot.gate

class Swap(wire1: Qubit, wire2: Qubit) extends NativeGate {
  val name = "swap"
  val wires = List(wire1, wire2)
  val params = List()
  lazy val inverse = Swap(wire1, wire2)
}
