package qdot.algorithm

import qdot.circuit.Circuit
import qdot.gate.{CPhase, Hadamard, Op}

class QFT[N <: Int](ops: List[Op]) extends Circuit[N](ops)

object QFT {
  def apply[N <: Int](dim: Int): QFT[N] = {
    val wires = (0 to dim-1).toList
    val qftGates = wires.dropRight(1).foldLeft(List[Op]())((gateSeq, nextWire) => {
      //append gate seequence for nextWire
      gateSeq ++ List(Hadamard(nextWire)) ++ (2 to dim-nextWire).toList.map(rootOfUnity => CPhase(rootOfUnity, rootOfUnity+nextWire-1, nextWire))
    }) ++ List(Hadamard(dim-1))
    new QFT(qftGates)
  }
}
