package qdot.algorithm

import breeze.linalg.DenseVector
import breeze.optimize.{ApproximateGradientFunction, LBFGS}
import qdot.backend.IBMQBackend
import qdot.circuit.Circuit
import qdot.compiler.QASMCompiler
import qdot.gate.{CNOT, Qubit, UGate, Unitary, Hadamard}

import scala.math.Pi

//graph: adjacency matrix of a graph, True = edge between ij. It must be symmetric. Diagonal is False (no self loop)
class QAOA(graph: List[List[Boolean]], ibmq: IBMQBackend) {

  val dim = graph.length
  //All edges, not double counted
  val extractedEdge: List[(Int, Int)] = extractEdges(graph)

  def costLayer(params: List[Double], qubits: List[Qubit]): List[Unitary] = {
    extractedEdge.map{ case (startNode, endNode) => List(CNOT(qubits(startNode), qubits(endNode)), UGate(0,0,params.head,qubits(endNode)), CNOT(qubits(startNode), qubits(endNode)))}.flatten
  }

  //4 RX gates, one param
  def mixingLayer(params: List[Double], qubits: List[Qubit]): List[Unitary] = {
    qubits.map(UGate(params.head, -Pi/2, Pi/2, _))
  }

  private def constructQAOA(mixingParams: List[Double], costParams: List[Double]): Circuit[_] = {
    val qubits = Qubit.listOf(dim)
    val initState = Circuit(List(), qubits).add(qubits.map(Hadamard(_)))
    initState.add(costLayer(costParams, qubits)).add(mixingLayer(mixingParams, qubits)).measureAll()
  }

  def executeQAOACircuit(mixingParams: List[Double], costParams: List[Double]): Double = {
    val init = constructQAOA(mixingParams, costParams)
    QASMCompiler.toQASM(init, dim)
    val result = ibmq.submitQASMToIBMQ
    val parsed = ibmq.parseIBMQOutput(result)
    maxCutBitStrExpectation(parsed)
  }

  def optimize(mixingInit: List[Double], costInit: List[Double]): (List[Double], List[Double]) = {
    val mixingParamLength = mixingInit.length
    val costParamLength = costInit.length
    val paramLegnth = mixingParamLength + costParamLength

    //Remote quantum execution
    def qaoaCircuit(params: DenseVector[Double]): Double = {
      executeQAOACircuit(params.toArray.slice(0,mixingParamLength).toList, params.toArray.slice(mixingParamLength, paramLegnth).toList)
    }
    //Default epsilon 1E-05
    val approxF = ApproximateGradientFunction(qaoaCircuit, 0.01)
    //LBFGS DOES NOT CONVERGE IN QISKIT
    val lbfgs = new LBFGS[DenseVector[Double]](maxIter=10, m=5)

    val optimumParams: DenseVector[Double] = lbfgs.minimize(approxF, DenseVector((mixingInit++costInit).toArray))

    (optimumParams.toArray.slice(0, mixingParamLength).toList, optimumParams.toArray.slice(mixingParamLength, paramLegnth).toList)
  }

  private def maxCutBitStrExpectation(res: Map[List[Int], Int]): Double = {
    val edgesAcrossCutSummed = res.map(bitStrCount => calculateCutDelta(bitStrCount._1, extractedEdge)*bitStrCount._2).toList.sum
    edgesAcrossCutSummed/res.values.toList.sum
  }

  //IBM Qiskit Max Cut Delta formula
  private def calculateCutDelta(bitStr: List[Int], graphEdge: List[(Int, Int)]): Double = {
    graphEdge.map{case (startNode, endNode) => if bitStr(startNode) != bitStr(endNode) then -1 else 0}.sum
  }

  //all edges not double counted
  private def extractEdges(graph: List[List[Boolean]]): List[(Int, Int)] = {
    val doubleConutedEdges = graph.map(span => span.zipWithIndex.filter(_._1)).zipWithIndex.map{case (edges, node) => edges.map(edge => (edge._2, node))}.flatten
    doubleConutedEdges.filter(edge => edge._1 < edge._2)
  }

}
