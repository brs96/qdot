package qdot.gate

class CZ(control: Int, target: Int) extends Gate {
  val name = "cz"
  val wires = List(control, target)
  val params = List()
}
