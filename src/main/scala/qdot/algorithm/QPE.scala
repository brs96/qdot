package qdot.algorithm

import qdot.circuit.Circuit
import qdot.gate.{CustomGate, Unitary, Op, Hadamard, Qubit, Measurement}
import scala.math.pow

class QPE[N <: Int](ops: List[Op], wires: List[Qubit]) extends Circuit[N](ops: List[Op], wires: List[Qubit])

object QPE {
  //numDigits: number of digits estimated for phase
  //cUnitary: the controlled unitary, if it's custom cu then decomposition must have been defined
  //initialState: Circuit with first $numDigits = 0 tensored with eigenstate. Circuit class atm doesn't support tensor (can't shift index in Circuit atm).
  //i.e numDigits + dim(cUnitary) - 1 = dim(initialState)
  //dim: dim(initialState
  def apply[N <: Int, M <: Int](digits: List[Qubit], cUnitary: (Qubit, List[Qubit]) => Unitary, eigenState: Unitary): QPE[N] = {
    val numDigits = digits.length
    val hadamardMap: List[Unitary] = digits.map(Hadamard(_))
    val cUnitaryMap = digits.zipWithIndex.reverse.map(control => List.fill(pow(2, numDigits-1-control._2).toInt)(cUnitary(control._1, eigenState.wires))).flatten
    val inverseQFT = QFT[M](digits).inverse
    val measureDigits = digits.map(Measurement(_))
    val circuit = Circuit[N](hadamardMap ++ List(eigenState) ++ cUnitaryMap, digits ++ eigenState.wires).add(inverseQFT.opSeq).add(measureDigits)

    new QPE(circuit.opSeq, circuit.qubits)
  }
}