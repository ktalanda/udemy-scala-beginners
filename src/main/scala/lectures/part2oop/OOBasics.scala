package lectures.part2oop

import org.w3c.dom.css.Counter

import java.time.Year

object OOBasics extends App {

  val person = new Person(name = "Kamil", age = 36)
  println(person.age)
  println(person.x)
  person.greet("Marta")
  person.greet()
}

class Person(name: String, val age: Int = 0) {
  val x = 2

  println(x + 5)

  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")

  def greet(): Unit = println(s"Hi I am $name")

  def this(name: String) = this(name, 0)

  val camus = new Writer(firstName = "Albert", lastName = "Camus", yearOfBorn = 1914)
  val theRebel = new Novel(name = "The Rebel", 1951, author = camus)

  println(theRebel.authorAge())

  val counter = new Counter
  counter.increment(10)
  counter.decrement(5)
}

class Novel(
             val name: String,
             val yearOfRelease: Int,
             val author: Writer
           ) {

  def authorAge(): Int = yearOfRelease - author.yearOfBorn

  def isWrittenByAuthor(writer: Writer): Boolean = author.isEqual(writer)

  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

class Writer(
              val firstName: String,
              val lastName: String,
              val yearOfBorn: Int
            ) {
  def fullName(): String = s"$firstName $lastName"

  def isEqual(writer: Writer): Boolean =
    (firstName == writer.firstName) && (lastName == writer.lastName) && (yearOfBorn == writer.yearOfBorn)
}

class Counter(val i: Int = 0) {
  def currentCount(): Int = i

  def increment: Counter = {
    println("incrementing")
    new Counter(i + 1)
  }

  def increment(value: Int): Counter = {
    if (value <= 0) this
    else increment.increment(value - 1)
  }

  def decrement: Counter = {
    println("decrementing")
    new Counter(i - 1)
  }

  def decrement(value: Int): Counter = {
    if (value <= 0) this
    else decrement.decrement(value - 1)
  }
}