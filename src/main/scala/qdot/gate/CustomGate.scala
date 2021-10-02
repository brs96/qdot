package qdot.gate

trait CustomGate extends Gate {
  def decomposition: List[Gate]
  def qasmDef: String =
    val strWithDigits =
      if (params.isEmpty) {
        s"gate $name ${wires.map(i => ('a'+i).asInstanceOf[Char]).mkString(",")}\n" +
          s"{\n" +
          s"${decomposition.map(_.toQASM).mkString}" + s"}\n"
      } else {
        ""
        //TO BE ADDED FOR PARAMETERIZED CUSTOM GATES
        //USE REFLECTION TO OBTAIN PARAMETER NAMES
      }
    //Then we need to replace q[int] gates into variables a,b,c.. e.g q[0] -> a. Limitation: dim < 26
    wires.foldLeft(strWithDigits)((renamedStr, nextInt) => renamedStr.replace(s"q[${nextInt.toString}]", ('a'+nextInt).asInstanceOf[Char].toString))

}