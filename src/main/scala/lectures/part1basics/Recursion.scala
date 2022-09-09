package lectures.part1basics

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion extends App {

  def factorial(n: BigInt): BigInt =
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n - I first need factorial of ${n - 1}")
      val result = n * factorial(n - 1)
      println(s"Computed factorial of $n")

      result
    }
  //  println(factorial(5000))

  def tailFactorial(n: BigInt): BigInt = {
    @tailrec
    def factorialHelper(x: BigInt, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // tail recursion

    factorialHelper(n, accumulator = 1)
  }

  //  println(tailFactorial(50000))

  def repeat(value: String, n: Int): String = {
    if (n <= 1) value
    else value + repeat(value, n - 1)
  }
  //  println(repeat("hello", 10))

  def tailRepeat(value: String, n: Int): String = {
    @tailrec
    def helper(value: String, n: Int, accumulator: String): String =
      if (n <= 1) accumulator
      else helper(value, n - 1, value + accumulator)

    helper(value, n, accumulator = value)
  }

  //  println(tailRepeat("hello", 1000000))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
      if (t <= 1) isStillPrime
      else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)
    }

    isPrimeTailRec(n / 2, isStillPrime = true)
  }
  //  println(isPrime(2003))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciTailRec(i: Int, last: Int, nextLast: Int): Int = {
      if (i >= n) last
      else fibonacciTailRec(i + 1, last + nextLast, last)
    }

    fibonacciTailRec(2, 1, 1)
  }

  println(fibonacci(1))
}
