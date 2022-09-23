package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple = (2, "hello Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello Scala", 2)

  // maps = keys -> values
  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 555), "Bogdan" -> 789).withDefaultValue(-1)
  // a -> b is syntactic sugar for (a, b)
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mariola"))

  // add a pairing
  val newPairing = "Mariola" -> 678
  val newPhonebook = phoneBook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap)
  // mapValues
  println(phoneBook.view.mapValues(number => "0245-" + number).toMap)
  println(phoneBook.flatMap(x => List(x._1)))

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Bogdan", 555)).toMap)
  val names = List("Bogdan", "Mariola", "Marian", "Bozena")
  println(names.groupBy(name => name.charAt(0)))

  println((phoneBook + ("BOGDAN" -> 600)).map(pair => pair._1.toLowerCase -> pair._2))

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bogdan"), "Mariola")
  println(network)
  println(friend(network, "Bogdan", "Mariola"))
  println(unfriend(friend(network, "Bogdan", "Mariola"), "Bogdan", "Mariola"))
  println(remove(friend(network, "Bogdan", "Mariola"), "Bogdan"))

  val people = add(add(add(empty, "Jim"), "Mary"), "Bob")
  val jimBob = friend(people, "Jim", "Bob")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.view.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(remove(testNet, "Bob")))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Bogdan", "Mariola"))

  val socialNetwork = SocialNetwork(Map())
  val bogdan = RealPerson("Bogdan")
  val mariola = RealPerson("Mariola")
  val marian = RealPerson("Marian")
  val bozena = RealPerson("Bozena")
  println(
    socialNetwork
      .add(bogdan)
      .add(mariola)
      .add(marian)
      .add(bozena)
      .friend(bogdan, mariola)
      .friend(marian, bozena)
      .remove(marian)
      .value
  )
}

sealed class Person
object NonExistingPerson extends Person
case class RealPerson(name: String) extends Person

class SocialNetwork(val value: Map[Person, Person]) {
  def add(person: Person) = new SocialNetwork(value + (person -> NonExistingPerson))

  def remove(person: Person) = new SocialNetwork(
    value.view.filterKeys(_ != person)
      .map(pair =>
        if (pair._2 == person) (pair._1 -> NonExistingPerson)
        else pair
      )
      .toMap)

  def friend(left: RealPerson, right: RealPerson) = new SocialNetwork(
    value.map(pair =>
      if (pair._1 == left) pair._1 -> right
      else if (pair._1 == right) pair._1 -> left
      else pair
    )
  )
}
