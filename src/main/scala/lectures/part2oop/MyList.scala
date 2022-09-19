package lectures.part2oop

import lectures.part2oop.Generics.MyList

import java.util.function.Predicate
import scala.runtime.Nothing$

object ListTest extends App {
  val listOfIntegers = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherListOfIntegers = Cons(4, Cons(5, Cons(6, Empty)))
  val listOfStrings = Cons("hello", Cons("Scala", Cons("!!", Empty)))
  println(listOfIntegers)
  println(listOfStrings)
  println(listOfIntegers.map((elem: Int) => elem * 2))
  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap((elem: Int) => Cons(elem, Cons(elem + 1, Empty))))
}

abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = s"[$printElements]"

  // higher order functions
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def printElements: String = ""

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def printElements: String =
    if (t.isEmpty) s"$h"
    else s"$h ${t.printElements}"

  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

  def add[B >: A](element: B): MyList[B] = Cons(element, this)

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: (A) => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
}
