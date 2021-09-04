package qdot.circuit

import qdot.gate.{Gate, Op, Qubit}

class Circuit[N <: Int](ops: List[Op]) {

  val opSeq: List[Op] = ops

  def add(op: Op): Circuit[N] = Circuit[N](opSeq ++ List(op))

}

