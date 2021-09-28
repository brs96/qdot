package qdot.gate

class CNOT(control: Int, target: Int) extends Gate {
  val name = "cx"
  val wires = List(control, target)
  val params = List.empty[Int]
  lazy val inverse = CNOT(control, target)
}

