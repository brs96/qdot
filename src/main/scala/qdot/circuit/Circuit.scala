package qdot.circuit

import qdot.gate.{Gate, Measurement, Op}
import scala.reflect.ClassTag

class Circuit[N <: Int](ops: List[Op]) {

  val opSeq: List[Op] = ops

  def add(op: Op): Circuit[N] = Circuit[N](opSeq ++ List(op))

  def add(circuit: Circuit[N]): Circuit[N] = Circuit[N](opSeq ++ circuit.opSeq)

  def measureAll(dim: Int): Circuit[N] = Circuit[N](opSeq ++ (0 to dim-1).toList.map(wire => Measurement(wire)))

}

