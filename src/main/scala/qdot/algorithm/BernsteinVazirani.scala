package qdot.algorithm

import qdot.circuit.Circuit
import qdot.gate.{Hadamard, Measurement, NativeGate, Op, Qubit, UGate, ZGate}


class BernsteinVazirani[N <: Int](ops: List[Op], wires: List[Qubit]) extends Circuit[N](ops, wires)

object BernsteinVazirani {

  def apply[N <: Int](bitString: List[Int], wires: List[Qubit]): BernsteinVazirani[N] = {
    val hadamardMap: List[Hadamard] = wires.map(qubit => Hadamard(qubit))
    val bitStringOracle: List[NativeGate] = bitString.zipWithIndex.map(pair => if (pair._1 == 0) UGate(0,0,0,wires(pair._2)) else ZGate(wires(pair._2)))
    val measurements: List[Measurement] = wires.map(Measurement(_))
    new BernsteinVazirani[N](hadamardMap ++ bitStringOracle ++ hadamardMap ++ measurements, wires)
  }

}
