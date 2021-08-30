package qdot.gate

import scala.compiletime.ops.int._

trait Qubit[N <: Int] {

  inline def hadamard(inline wire: Int): Qubit[N]

  def cnot(wire1: Int, wire2: Int): Qubit[N]

  def measure(wire: Int): (Int, Qubit[N-1])

}
