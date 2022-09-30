package lectures.part4pm

object BracelessSyntax extends App {

  // if - expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpression_v4 =
    if 2 > 3 then
      "bigger"
    else
      "smaller"

    val anIfExpression_v5 =
      if 2 > 3 then
        val result = "bigger"
        result
      else
        val result = "smaller"
        result

    // scala 3 one-liner
    val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

    println(anIfExpression_v5)

    // for comprehensions
    val aForComprehension = for {
      n <- List(1, 2, 3)
      s <- List("black", "white")
    } yield s"$n$s"

    val aForComprehension_v2 =
      for
        n <- List(1, 2, 3)
        s <- List("black", "white")
      yield s"$n$s"

    // pattern matching
    val meaningOfLife = 42
    val aPatternMatching = meaningOfLife match {
      case 1 => "the one"
      case 2 => "double or nothing"
      case _ => "something else"
    }

    // scala 3
    val aPatternMatching_v2 =
      meaningOfLife match
        case 1 => "the one"
        case 2 => "double or nothing"
        case _ => "something else"

    // methods without braces
    def computeMeaningOfLife(arg: Int): Int =
      val partialResult = 40

      partialResult + 2

    // class definition with significant indentation (same for traits, objects, enums etc)
    class Animal: // compiler expects the body of Animal
      def eat(): Unit =
        println("I'm eating")
      end eat

      def grow(): Unit =
        println("I'm growing")
      end grow
    end Animal

    println(computeMeaningOfLife(1))

    // anonymous classes
    val aSpecialAnimal = new Animal:
      override def eat(): Unit = println("I'm special")
}
