package qdot.gate

import scala.compiletime.package$package.error

class CNOT(control: Int, target: Int) extends Gate {
  val name = "cx"
  val wires = List(control, target)
}

object CNOT {
  inline def apply[N <: Int](inline control: Int, target: Int) =
    inline if (control < valueOf[N] && target < valueOf[N]) new CNOT(control, target) else error("CNOT Compile time creation dimension error")
}
