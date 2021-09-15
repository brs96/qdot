package qdot.gate

class Measurement(wire: Int) extends Op {
  val name = "measure"
  val toQASM: String = s"${name} q[${wire}] -> c$wire[0];\n"
}
