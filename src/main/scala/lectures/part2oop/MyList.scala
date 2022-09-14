package lectures.part2oop

import scala.runtime.Nothing$

object ListTest extends App {
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings = new Cons("hello", new Cons("Scala", new Cons("!!", Empty)))
  println(listOfIntegers)
  println(listOfStrings)

}

abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = s"[$printElements]"
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def printElements: String = ""

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def printElements: String =
    if (t.isEmpty) s"$h"
    else s"$h ${t.printElements}"

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
}
