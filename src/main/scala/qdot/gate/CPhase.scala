package qdot.gate

class CPhase(lb: Double, control: Int, target: Int) extends Gate {
  val name = "cu1"
  val wires = List(control, target)
  val params = List(lb)
  lazy val inverse = CPhase(-lb, control, target)
}
