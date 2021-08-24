//package qdot
import qdot.gate.*

@main def hello: Unit = {

  //val circ: Qubit[2] => Qubit[2] = _.hadamard(0).cnot(2, 3)

  val init: SimulatedQubit[2] = SimulatedQubit(List(List(0.1, 0.2)))
  val state = init.hadamard(1).cnot(2, 3)
  print(state.matrix)
  print("\n")


  val qasmInstructions: QASMSet[2] = QASMSet.init(2).hadamard(0).cnot(2, 3)
  print(qasmInstructions.QASMStr)
}
