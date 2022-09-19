package part3fp

object WhatsAFunction extends App {
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)

  val adder: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  val concat = new Function2[String,String,String] {
    override def apply(left: String, right: String): String = left + right
  }
  println(concat("Hello", " Scala"))

  
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
