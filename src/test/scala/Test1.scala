import org.junit.Test
import org.junit.Assert.*

class Test1:

  val msg = "msg"

  @Test def t1(): Unit =
    assertEquals("I was compiled by Scala 3. :)", msg)
