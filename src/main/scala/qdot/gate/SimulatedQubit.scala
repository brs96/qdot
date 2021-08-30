package qdot.gate

import scala.compiletime.ops.int.-
import scala.compiletime.package$package.error

class SimulatedQubit[N <: Int](m: List[List[BigDecimal]]) extends Qubit[N] {

  val matrix: List[List[BigDecimal]] = m

  inline def hadamard(inline wire: Int): SimulatedQubit[N] = {
    inline if (wire < valueOf[N]) SimulatedQubit(matrix.map(list => list.map(decimal => decimal + 1))) else error("Compile time dimension error")
  }
  //  //breaks inline
  //  //anonymous function means can't inline method
  // val circ: Qubit[2] => Qubit[2] = _.hadamard(0).cnot(2, 3)
  //  // looping var i means can't inline param
  //  def constructCircuit(s: SimulatedQubit[2]): SimulatedQubit[2] = {
  //    var i = 0
  //    var state = s
  //    while (i <= 4) {
  //      state.hadamard(i)
  //      i += 1
  //    }
  //    state
  //  }

  def cnot(wire1: Int, wire2: Int): SimulatedQubit[N] = SimulatedQubit(matrix.map(list => list.map(decimal => decimal + 1)))

  def measure(wire: Int): (Int, SimulatedQubit[N-1]) = {
    if (matrix(wire)(wire) == 1) (1, SimulatedQubit(matrix.tail))
    else (0, SimulatedQubit(matrix.tail))
  }
}

