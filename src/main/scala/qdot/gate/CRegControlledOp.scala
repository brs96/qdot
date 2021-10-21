package qdot.gate

class CRegControlledOp(val classicalReg: Int, val classicalValue: Int, val op: Op) extends Op {
  def name = "classically controlled operation"
  val wires = op.wires
}
