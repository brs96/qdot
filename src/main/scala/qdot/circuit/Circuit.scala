package qdot.circuit

import qdot.gate.{Gate, Op}

class Circuit[N <: Int](ops: List[Op]) {

  val opSeq: List[Op] = ops

  def add(op: Op): Circuit[N] = Circuit[N](opSeq ++ List(op))

  def add(circuit: Circuit[N]): Circuit[N] = Circuit[N](opSeq ++ circuit.opSeq)

}

