package qdot.gate

trait Gate {
  def name: String
  def wires: List[Int]
}
