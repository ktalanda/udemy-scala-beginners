package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doubler: Int => Int = x => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  val justDoSomething: () => Int = () => 3

  println(justDoSomething)
  println(justDoSomething())

  // curly braces with lambdas
  val stringoInt = { (str: String) => str.toInt }

  // MORE syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // (a,b) => a + b

  val superAdder = (x: Int) => (y: Int) => x + y
  println(superAdder(1)(2))
}
