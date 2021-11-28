package qdot.circuit

import qdot.gate.{CustomGate, Unitary, Measurement, Op, Swap, Qubit}

import scala.compiletime.ops.int.+

class Circuit[N <: Int](val ops: List[Op], val qubits: List[Qubit]) {

  val dim = qubits.length

  def add(op: Op): Circuit[N] = Circuit[N](ops ++ List(op), qubits)

  def add(operations: List[Op]): Circuit[N] = Circuit[N](ops ++ operations, qubits)

  def add[T <: Circuit[N]](circuit: T): Circuit[N] = Circuit[N](ops ++ circuit.ops, qubits)

  def measureAll(): Circuit[N] = Circuit[N](ops ++ qubits.map(Measurement(_)), qubits)

}

