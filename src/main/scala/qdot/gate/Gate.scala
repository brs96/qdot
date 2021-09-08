package qdot.gate

trait Gate extends Op {
  def wires: List[Int]
  def params: List[_]
  def toQASM: String =
    if (params.isEmpty) {
      s"${name} ${wires.foldLeft("")((wireSeq, nextWire) => wireSeq ++ s"q[$nextWire],").dropRight(1) ++ ";\n"}"
    } else {
      s"${name}(${params.foldLeft("")((paramSeq, nextParam) => paramSeq ++ s"$nextParam,").dropRight(1)}) ${wires.foldLeft("")((wireSeq, nextWire) => wireSeq ++ s"q[$nextWire],").dropRight(1) ++ ";\n"}"
    }
}