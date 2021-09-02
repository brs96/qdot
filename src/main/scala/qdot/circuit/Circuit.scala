package qdot.circuit

import qdot.gate.{Gate, Qubit}

class Circuit[N <: Int](gates: List[Gate]) {

  val gateSeq: List[Gate] = gates

  def add(gate: Gate): Circuit[N] = Circuit[N](gateSeq ++ List(gate))

}

