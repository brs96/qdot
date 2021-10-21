package qdot.gate

class CNOT(control: Qubit, target: Qubit) extends NativeGate {
  val name = "cx"
  val wires = List(control, target)
  val params = List.empty[Int]
  lazy val inverse = CNOT(control, target)
}

