package qdot.compiler

import qdot.circuit.Circuit

import java.io.{File, FileWriter, PrintWriter}
import scala.quoted.Type

object QASMCompiler {

  def toQASM(circuit: Circuit[_]) = {
    val writer = new PrintWriter(new File("src/main/qasm/data.qasm"))
    val init = initQASM(circuit)
    writer.write(init)
    parseCircuit(circuit, writer)
    //TODO Impl measurement
    writer.write("measure q[0] -> c[0];\nmeasure q[1] -> c[1];")
    writer.close()
  }

  def initQASM[N <: Int](circuit: Circuit[N]): String = {
    //TODO Compile time reflection for N <: Int N-String
    val dimStr = "2"
    s"OPENQASM 2.0;\ninclude \"qelib1.inc\";\nqreg q[${dimStr}];\ncreg c[${dimStr}];\n"
  }

  def parseCircuit(circuit: Circuit[_], writer: PrintWriter) = {
    circuit.gateSeq.map(gate => {
      writer.write(s"${gate.name} ${parseWires(gate.wires)}")
    })
  }

  def parseWires(wires: List[Int]): String = {
    wires.foldLeft("")((wireSeq, nextWire) => wireSeq ++ s"q[$nextWire],").dropRight(1) ++ ";\n"
  }

}
