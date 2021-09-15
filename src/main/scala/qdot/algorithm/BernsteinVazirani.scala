package qdot.algorithm

import qdot.circuit.Circuit
import qdot.gate.{Hadamard, Op, UGate, ZGate}


class BernsteinVazirani[N <: Int](ops: List[Op]) extends Circuit[N](ops)

object BernsteinVazirani {

  def apply[N <: Int](bitString: List[Int], dim: Int): BernsteinVazirani[N] = {
    val wires = (0 to dim-1).toList
    val hadamardMap = wires.map(wire => Hadamard(wire))
    val bitStringOracle = bitString.zipWithIndex.map((bit,index) => if (bit == 0) UGate(0,0,0,index) else ZGate(index))
    new BernsteinVazirani[N](hadamardMap ++ bitStringOracle ++ hadamardMap)
  }

}
