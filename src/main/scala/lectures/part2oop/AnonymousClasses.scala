package lectures.part2oop

object AnonymousClasses extends App {

  trait Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahahahhahaha")
  }

  //  class AnonymousClasses$$anon$1 extends Animal {
  //    override def eat: Unit = println("ahahahahhahaha")
  //  }
  //  val funnyAnimal = new AnonymousClasses$$anon$1()

  println(funnyAnimal.eat)

//  class Person(name: String) {
//    def sayHi: Unit = println(s"Hi, my name is $name, how can I help")
//  }
//
//  val jim = new Person("Jim") {
//    override def sayHi: Unit = println("Hey ho")
//  }
}
