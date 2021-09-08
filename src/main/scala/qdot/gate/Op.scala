package qdot.gate

trait Op {
  def name: String
  def toQASM: String
}