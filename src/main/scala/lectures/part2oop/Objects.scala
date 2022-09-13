package lectures.part2oop

object Objects {

  object Person {
    val N_EYES = 2

    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {

  }
  // Companions

  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person.canFly)

    val mary = new Person("Mary")
    val ben = new Person("Ben")
    println(mary == ben)

    val person1 = Person
    val person2 = Person
    println(person1 == person2)

    val bobby = Person(mary, ben)
  }
}
