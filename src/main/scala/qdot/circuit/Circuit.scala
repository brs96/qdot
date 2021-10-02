package qdot.circuit

import qdot.gate.{CustomGate, Gate, Measurement, Op}

import scala.reflect.ClassTag

class Circuit[N <: Int](ops: List[Op], customGates: List[CustomGate] = List()) {

  val customGateSeq: List[CustomGate] = customGates

  val opSeq: List[Op] = ops

  def add(op: Op): Circuit[N] = { op match
    case op: CustomGate => Circuit[N](opSeq ++ List(op), (customGateSeq++List(op)).groupBy(_.name).map(_._2.head).toList)
    case op: Op => Circuit[N](opSeq ++ List(op), customGateSeq)
  }

  def add(circuit: Circuit[N]): Circuit[N] = Circuit[N](opSeq ++ circuit.opSeq, (customGateSeq++circuit.customGateSeq).groupBy(_.name).map(_._2.head).toList)

  def measureAll(dim: Int): Circuit[N] = Circuit[N](opSeq ++ (0 to dim-1).toList.map(wire => Measurement(wire)), customGateSeq)

}

