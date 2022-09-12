package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(nickname: String): Person = new Person(
      name = s"$name $nickname",
      favoriteMovie = favoriteMovie,
      age = age
    )

    def unary_! : String = s"${name}, what the heck!"

    def unary_+ : Person = new Person(
      name = name,
      favoriteMovie = favoriteMovie,
      age = age + 1
    )

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def apply(persistence: Int) = s"$name watched $favoriteMovie $persistence times"

    def learns(subject: String): String = s"$name learns $subject"

    def learns: String = learns("Scala")
  }

  val mary = new Person("Mary", "Inception", age = 30)
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation == operator notation
  val tom = new Person("Tom", "Fight Club", age = 31)
  println(mary.+(tom))
  println(mary + tom)

  println(1 + 2)
  println(1.+(2))

  // Prefix notation
  val x = -1
  val y = 1.unary_-
  // unary_ prefix works with - + ~ !
  println(!mary)

  //Postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary())

  println((mary + "test") ())
  println((+(+mary)).age)

  println(mary learns "something")
  println(mary learns)
  println(mary(persistence = 2))
}
