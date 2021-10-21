package qdot.gate

trait Unitary extends Op {
  def params: List[_]
  def inverse: Unitary
  def decomposition: List[NativeGate]
  def tensor(other: Unitary): Unitary = CustomGate("tensor gate", wires ++ other.wires, params ++ other.params, decomposition ++ other.decomposition)
}