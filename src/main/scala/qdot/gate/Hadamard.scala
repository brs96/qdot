package qdot.gate

import scala.compiletime.package$package.error


class Hadamard(wire: Int) extends Gate {
  val name = "h"
  val wires = List(wire)
}


object Hadamard {
  inline def apply[N <: Int](inline wire: Int) = inline if (wire < valueOf[N]) new Hadamard(wire) else error("Hadamard Compile time creation dimension error")
}