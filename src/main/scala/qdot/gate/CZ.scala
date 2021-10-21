package qdot.gate

class CZ(control: Qubit, target: Qubit) extends NativeGate {
  val name = "cz"
  val wires = List(control, target)
  val params = List()
  lazy val inverse = CZ(control, target)
}
