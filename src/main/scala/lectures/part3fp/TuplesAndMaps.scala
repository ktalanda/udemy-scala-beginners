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
}
