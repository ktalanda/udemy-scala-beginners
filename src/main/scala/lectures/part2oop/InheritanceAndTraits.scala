package lectures.part2oop

object InheritanceAndTraits extends App {

  sealed class Animal {
    val creatureType = "wild"

    def eat: Unit = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      super.eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  class Dog(override val creatureType: String) extends Animal {
    //    override val creatureType: String = "domestic"
    override def eat: Unit = println("crunch crunch")
  }

  val dog = new Dog(creatureType = "K9")
  dog.eat
  println(dog.creatureType)

  // type substitution, polymorphism
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat
}
