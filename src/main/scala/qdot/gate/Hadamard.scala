package qdot.gate

import scala.compiletime.package$package.error


class Hadamard(wire: Int) extends Gate

object Hadamard {
  inline def apply[N <: Int](inline wire: Int) = inline if (wire < valueOf[N]) new Hadamard(wire) else error("Hadamard Compile time creation dimension error")
}