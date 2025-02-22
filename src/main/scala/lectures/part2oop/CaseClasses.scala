package lectures.part2oop

object CaseClasses extends App {

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  println(jim)

  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. CCs have handy copy method
  val jim3 = jim.copy(age = 12)
  println(jim3)

  // 5. CCs have companion objects
  val thePerson = Person
  val mary = Person(name = "Mary", age = 30)

  // 6. CCs are serializable

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }


}
