package lectures.part3fp

object HOFsCurries extends App {

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF)

  // map, flatMap, filter in MyList

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(2))

  val superAdder: Int => Int => Int = x => y => x + y
  val add3 = superAdder(3)
  println(add3(10))

  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  def toCurry[A, B, C](f: (A, B) => C): A => B => C = x => y => f(x, y)
  def fromCurry[A, B, C](f: A => B => C): (A, B) => C = (x, y) => f(x)(y)

  val test = (x: Int, y: Int) => x + y
  println(test(1,2))
  val curriedTest = toCurry(test)
  println(curriedTest(1)(2))
  val uncurriedTest = fromCurry(curriedTest)
  println(uncurriedTest(1,2))

  def compose[A, B, C](f: A => B, g: C => A): C => B = x => f(g(x))
  def andThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))

  def toString(x: Int): String = x.toString
  def fromString(x: String): Int = x.toInt

  def doNothingToString = compose(toString, fromString)
  println(doNothingToString("10"))
  def doNothingToInt = andThen(toString, fromString)
  println(doNothingToInt(10))
}
