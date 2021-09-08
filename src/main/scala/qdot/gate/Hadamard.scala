package qdot.gate

import scala.compiletime.package$package.error


class Hadamard(wire: Int) extends Gate {
  val name = "h"
  val wires = List(wire)
  val params = List.empty[Int]
}


object Hadamard {
  def apply[N <: Int](wire: Int) = new Hadamard(wire)
}