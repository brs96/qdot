package qdot.compiler

import qdot.circuit.Circuit
import qdot.gate.{Gate, Measurement, Op}

import java.io.{File, FileWriter, PrintWriter}
import scala.quoted.Type

object QASMCompiler {

  def toQASM(circuit: Circuit[_], dim: Int) = {
    val writer = new PrintWriter(new File("src/main/qasm/data.qasm"))
    val init = initQASM(circuit, dim)
    writer.write(init)
    parseCircuit(circuit, writer)
    writer.close()
  }

  //TODO Compile time reflection for N <: Int N-String
  def initQASM[N <: Int](circuit: Circuit[N], dim: Int): String = {
    s"OPENQASM 2.0;\ninclude \"qelib1.inc\";\nqreg q[${dim.toString}];\n" ++ cregList(dim)
  }

  def cregList(dim: Int): String = {
    (0 to dim-1).toList.foldLeft("")((str, nextCreg) => str ++ s"creg c$nextCreg[1];\n")
  }

  def parseCircuit(circuit: Circuit[_], writer: PrintWriter) = {
    circuit.opSeq.map(op => writer.write(op.toQASM))
  }

}
