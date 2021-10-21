package qdot.gate

class Measurement(w: Qubit) extends Op {
  val name = "measure"
  val wires = List(w)
}
