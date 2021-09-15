package qdot.gate

//phase only root of unity for now for QFT, for rootOfUnity = n, phase is 2*pi*i/2^n
class CPhase(rootOfUnity: Int, control: Int, target: Int) extends Gate {
  val name = "cu1"
  val wires = List(control, target)
  val params = List(rootOfUnity).map(i => "pi/" ++ math.pow(2,i-1).toInt.toString)
}
