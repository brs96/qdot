//package qdot
import qdot.gate._


@main def hello: Unit = {
  val init: SimulatedQubit[2] = SimulatedQubit(List(List(0.1, 0.2)))
  val state: SimulatedQubit[2] = init.hadamard(0).cnot(2, 3)
  print(state.matrix)
  print("\n")


  val qasmInstructions: QASMSet[2] = QASMSet.init(2).hadamard(0).cnot(2, 3)
  print(qasmInstructions.QASMStr)
}
