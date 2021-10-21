package qdot.gate

trait Op {
  def name: String
  def wires: List[Qubit]
}