package qdot.gate

class CRegControlledOp(classicalReg: Int, classicalValue: Int, op: Op) extends Op {
  def name = "classically controlled operation"
  def toQASM = s"if(c$classicalReg==$classicalValue) ${op.toQASM}"
}
