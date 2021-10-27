package qdot.compiler

import qdot.circuit.Circuit
import qdot.gate.{CRegControlledOp, CustomGate, Unitary, Measurement, Op, NativeGate, Qubit}

import java.io.{File, FileWriter, PrintWriter}
import scala.quoted.Type

object QASMCompiler {

  def toQASM(circuit: Circuit[_]) = {
    val dim = circuit.dim
    val writer = new PrintWriter(new File("src/main/qasm/data.qasm"))
    initQASM(dim, writer)
    parseCircuit(circuit, writer)
    writer.close()
  }

  //TODO Compile time reflection for N <: Int N-String
  def initQASM[N <: Int](dim: Int, writer: PrintWriter) = {
    val initStr = s"OPENQASM 2.0;\ninclude \"qelib1.inc\";\nqreg q[${dim.toString}];\n" ++ cregList(dim)
    writer.write(initStr)
  }

  def cregList(dim: Int): String = {
    (0 to dim-1).toList.foldLeft("")((str, nextCreg) => str ++ s"creg c$nextCreg[1];\n")
  }

  def parseCircuit(circuit: Circuit[_], writer: PrintWriter) = {
    circuit.opSeq.map(op => writer.write(buildQASM(op, circuit.qubits)))
  }

  def buildQASM(op: Op, qubits: List[Qubit]): String = op match
    case measurement: Measurement => s"${measurement.name} q[${qubits.indexOf(measurement.wires.head)}] -> c${qubits.indexOf(measurement.wires.head)}[0];\n"
    case cregControlledOp: CRegControlledOp => s"if(c${cregControlledOp.classicalReg}==${cregControlledOp.classicalValue}) ${buildQASM(cregControlledOp.op, qubits)}"
    case nativeGate: NativeGate => {
      if (nativeGate.params.isEmpty) {
        s"${nativeGate.name} ${nativeGate.wires.foldLeft("")((wireSeq, nextWire) => wireSeq ++ s"q[${qubits.indexOf(nextWire)}],").dropRight(1) ++ ";\n"}"
      } else {
        s"${nativeGate.name}(${nativeGate.params.foldLeft("")((paramSeq, nextParam) => paramSeq ++ s"$nextParam,").dropRight(1)}) ${nativeGate.wires.foldLeft("")((wireSeq, nextWire) => wireSeq ++ s"q[${qubits.indexOf(nextWire)}],").dropRight(1) ++ ";\n"}"
      }
    }
    case generalUnitary: Unitary => generalUnitary.decomposition.map(buildQASM(_, qubits)).mkString("")
}
