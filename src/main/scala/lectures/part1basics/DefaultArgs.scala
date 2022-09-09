package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {
  @tailrec
  def factorial(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else factorial(n - 1, n * acc)

  println(factorial(10))

  def savePicture(format: String = "jpg", width: Int, height: Int): Unit = println("saving picture")
  savePicture(width = 800, height = 600)
}
