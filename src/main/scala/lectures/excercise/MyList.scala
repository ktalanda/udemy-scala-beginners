package lectures.excercise

import lectures.part2oop.Generics.MyList

import java.util.function.Predicate
import scala.collection.SeqView.Sorted
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

  println(listOfIntegers.sort((x, y) => y-x))

  println(anotherListOfIntegers.zipWith(listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(0)(_+_))

  for {
    n <- listOfStrings
  } println(n)
}

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = s"[$printElements]"
  def ++[B >: A](list: MyList[B]): MyList[B]

  // higher order functions
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
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
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists have not the same length")
    else Empty
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def printElements: String =
    if (t.isEmpty) s"$h"
    else s"$h ${t.printElements}"
  def add[B >: A](element: B): MyList[B] = Cons(element, this)
  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)
  def map[B](transformer: (A) => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }
  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    val sortedTail = tail.sort(compare)
    insert(h, sortedTail)
  }
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists have not the same length")
    else Cons(zip(h, list.head), tail.zipWith(list.tail, zip))
  }
  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)
}
