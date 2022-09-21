package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  // iterations
  println(numbers.flatMap(n => chars.flatMap(c => colors.map(color => s"$c $n-$color"))))

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield s"$c $n-$color"
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  list.map { x =>
    x * 2
  }
}



