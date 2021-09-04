package qdot.compiler

import qdot.circuit.Circuit
import qdot.gate.{Gate, Measurement, Op}

import java.io.{File, FileWriter, PrintWriter}
import scala.quoted.Type

object QASMCompiler {

  def toQASM(circuit: Circuit[_]) = {
    val writer = new PrintWriter(new File("src/main/qasm/data.qasm"))
    val init = initQASM(circuit)
    writer.write(init)
    parseCircuit(circuit, writer)
    writer.close()
  }

  def initQASM[N <: Int](circuit: Circuit[N]): String = {
    //TODO Compile time reflection for N <: Int N-String
    val dimStr = "2"
    s"OPENQASM 2.0;\ninclude \"qelib1.inc\";\nqreg q[${dimStr}];\ncreg c[${dimStr}];\n"
  }

  def parseCircuit(circuit: Circuit[_], writer: PrintWriter) = {
    circuit.opSeq.map(op => {
      writer.write(s"${op.name} ${parseWires(op)}")
    })
  }

  def parseWires(op: Op): String = {
    op match {
      case m: Measurement => s"q[${m.wire}] -> c[${m.wire}];\n"
      case g: Gate => g.wires.foldLeft("")((wireSeq, nextWire) => wireSeq ++ s"q[$nextWire],").dropRight(1) ++ ";\n"
    }
  }

}
