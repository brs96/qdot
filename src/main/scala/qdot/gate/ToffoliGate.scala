package qdot.gate

class ToffoliGate(control1: Qubit, control2: Qubit, target: Qubit) extends CustomGate(
  "toffoli",
  List(control1, control2, target),
  List(),
  List(Hadamard(target), CNOT(control2, target), TGate(target).inverse, CNOT(control1, target),
  TGate(target), CNOT(control2, target), TGate(target).inverse, CNOT(control1, target), TGate(control2), TGate(target), Hadamard(target),
  CNOT(control1, control2), TGate(control1), TGate(control2).inverse, CNOT(control1, control2))) {

  override def inverse = ToffoliGate(control1, control2, target)
}
