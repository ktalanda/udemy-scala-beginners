package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //  println(x.length)
  // this ^^ will crash with NPE

  // 1. Ho to throw exceptions
  //  val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. How to catch exceptions

  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    getInt(false)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence return
    // use only for side effects
    println("finally")
  }

  println(potentialFail)

  // 3. How to define your own exception
  //  class MyException extends Exception
  //  val exception = new MyException
  //  throw exception

  // Crash with OOM
//  val array = Array.ofDim[Int](Int.MaxValue)

  // Crash with SOE
  def addString(x: String): Unit = x + addString(x)
//  addString("dasdadasdadas")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Float = {
      if (y == 0) throw new MathCalculationException
      else x/y
    }
  }

//  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception
}
