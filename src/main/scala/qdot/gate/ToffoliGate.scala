package qdot.gate

class ToffoliGate(control1: Int, control2: Int, target: Int) extends CustomGate {
  val name = "toffoli"
  val wires = List(control1, control2, target)
  val params = List()
  lazy val inverse = ToffoliGate(control1, control2, target)

  val decomposition = List(Hadamard(target), CNOT(control2, target), TGate(target).inverse, CNOT(control1, target),
    TGate(target), CNOT(control2, target), TGate(target).inverse, CNOT(control1, target), TGate(control2), TGate(target), Hadamard(target),
    CNOT(control1, control2), TGate(control1), TGate(control2).inverse, CNOT(control1, control2))

}
