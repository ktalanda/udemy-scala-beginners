package lectures.part4pm

import lectures.excercise.{MyList, Cons, Empty}

object AllThePatterns extends App {

  // 1 - constants
  val x: Any = "Scala"
  val constants = x match
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"

  // 2 =match anything
  // 2.1 wildcard
  val matchAnything = x match
    case _ =>

  // 2.2 variable
  val matchAVariable = x match
    case somehting => s"I've found $somehting"

  // 3 - tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match
    case (1, 1) =>
    case (somethig, 2) => s"I've found $somethig"

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match
    case (_, (2, v)) =>

  // 4 - case classes constructor
  // PMs can be nested with CCs as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty => ???
    case Cons(head, Cons(subhead, subtail)) =>
  }

  // 5 - list patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match
    case List(1, _, _, _) => //extractor
    case List(1, _*) => // ;ost of arbitrary length
    case 1 :: List(_) => // infix pattern
    case List (1, 2, 3) :+ 42 => // infix pattern

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match
    case list: List[Int] => // explicit type specifier
    case _ =>

  // 7 -name binding
  val nameBindingMatch = aList match
    case nonEmptyList @ Cons(_, _) => // name binding
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns

  // 8 - multi pattern
//  val multiPattern = aList match
//    case Empty | Cons(0, _) =>  // compound patterns multi-pattern

  // 9 - if guards
  val secondElementSpecial = aList match
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>


  val numbers: List[Int] = List(1, 2, 3)
  val numberMatch = numbers match
    case _: List[String] => "a list of strings"
    case _: List[Int] => "a list of numbers"
    case _ => "none"

  // Type erasure
  println(numberMatch)

}
