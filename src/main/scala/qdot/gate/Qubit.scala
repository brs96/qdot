package qdot.gate

class Qubit(val name: String)

object Qubit {
  def apply(name: Int) = new Qubit(name.toString)
  def apply(name: String) = new Qubit(name)
  def listOf(dim: Int) = (0 to dim-1).toList.map(Qubit(_))
}