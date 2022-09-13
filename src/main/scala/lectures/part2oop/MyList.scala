package lectures.part2oop

import scala.runtime.Nothing$

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail)
  println(list.add(4))
}

abstract class MyList {
  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(element: Int): MyList

  def printElements: String

  override def toString: String = s"[$printElements]"
}

object Empty extends MyList {
  def head: Int = throw new NoSuchElementException

  def tail: MyList = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def printElements: String = ""

  def add(element: Int): MyList = new Cons(element, Empty)
}

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h

  def tail: MyList = t

  def isEmpty: Boolean = false

  def printElements: String =
    if (t.isEmpty) s"$h"
    else s"$h ${t.printElements}"

  def add(element: Int): MyList = new Cons(element, this)
}
