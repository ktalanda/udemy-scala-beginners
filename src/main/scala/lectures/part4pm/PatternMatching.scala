package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" // _ = WILDCARD

  println(x)
  println(description)

  case class Person(name: String, age: Int)
  val bob = Person("bob", 20)

  val greeting = bob match
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  println(greeting)


  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expression: Expr): String = expression match
      case Number(n) => s"$n"
      case Sum(e1, e2) => show(e1) + "+" + show(e2)
      case Prod(e1, e2) => {
        def maybeShowParentheses(exp: Expr) = exp match
          case Prod(_, _) => show(exp)
          case Number(_) => show(exp)
          case _ => s"(${show(exp)})"

        s"${maybeShowParentheses(e1)} * ${maybeShowParentheses(e2)}"
      }


  println(show(Prod(Sum(Number(2), Number(3)), Number(5))))
}

