package qdot.circuit

import qdot.gate.{CustomGate, Unitary, Measurement, Op, Swap, Qubit}

import scala.compiletime.ops.int.+

class Circuit[N <: Int](ops: List[Op], val qubits: List[Qubit]) {

  val dim = qubits.length
  val opSeq: List[Op] = ops

  def add(op: Op): Circuit[N] = Circuit[N](opSeq ++ List(op), qubits)

  def add(operations: List[Op]): Circuit[N] = Circuit[N](opSeq ++ operations, qubits)

  def add[T <: Circuit[N]](circuit: T): Circuit[N] = Circuit[N](opSeq ++ circuit.opSeq, qubits)

  def measureAll(): Circuit[N] = Circuit[N](opSeq ++ qubits.map(Measurement(_)), qubits)

}

