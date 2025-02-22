package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x: Int = 42
  println(x)

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 11231
  val aLong: Long = 123123312312313
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  var aVariable: Int = 4
  aVariable = 5 // side effects
}
