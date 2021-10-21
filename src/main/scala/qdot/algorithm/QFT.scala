package qdot.algorithm

import qdot.circuit.Circuit
import qdot.gate.{CPhase, Unitary, Hadamard, Op, Swap, Qubit, NativeGate}

import scala.math.{Pi, pow}

class QFT[N <: Int](gates: List[Unitary], wires: List[Qubit]) extends Circuit[N](gates, wires) {
  def inverse: Circuit[N] = Circuit[N](gates.reverse, wires)
}

object QFT {
  def apply[N <: Int](wires: List[Qubit]): QFT[N] = {
    val dim = wires.length
    val qftGates: List[Unitary] = wires.dropRight(1).foldLeft(List[Unitary]())((gateSeq, nextWire) => {
      //append gate seequence for nextWire
      val index = wires.indexOf(nextWire)
      gateSeq ++ List(Hadamard(nextWire)) ++ (2 to dim-index).toList.map(rootOfUnity => CPhase(2*Pi/pow(2, rootOfUnity), wires(rootOfUnity+index-1), nextWire))
    }) ++ List(Hadamard(wires(dim-1)))
    val swapGates: List[NativeGate] = if (dim%2 == 0) (0 to (dim/2)-1).toList.map(index => Swap(wires(index), wires(dim-1-index))) else (0 to ((dim-1)/2)-1).toList.map(index => Swap(wires(index), wires(dim-1-index)))
    new QFT(qftGates ++ swapGates, wires)
  }
}
