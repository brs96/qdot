package qdot.gate

trait Gate extends Op {
  def wires: List[Int]
}