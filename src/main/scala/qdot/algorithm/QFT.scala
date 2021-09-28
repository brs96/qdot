package qdot.algorithm

import qdot.circuit.Circuit
import qdot.gate.{CPhase, Hadamard, Op, Swap}
import scala.math.{Pi,pow}

class QFT[N <: Int](ops: List[Op]) extends Circuit[N](ops)

object QFT {
  def apply[N <: Int](dim: Int): QFT[N] = {
    val wires = (0 to dim-1).toList
    val qftGates = wires.dropRight(1).foldLeft(List[Op]())((gateSeq, nextWire) => {
      //append gate seequence for nextWire
      gateSeq ++ List(Hadamard(nextWire)) ++ (2 to dim-nextWire).toList.map(rootOfUnity => CPhase(2*Pi/pow(2, rootOfUnity), rootOfUnity+nextWire-1, nextWire))
    }) ++ List(Hadamard(dim-1))
    val swapGates: List[Op] = if (dim%2 == 0) (0 to (dim/2)-1).toList.map(wire => Swap(wire, dim-1-wire)) else (0 to ((dim-1)/2)-1).toList.map(wire => Swap(wire, dim-1-wire))
    new QFT(qftGates ++ swapGates)
  }
}
