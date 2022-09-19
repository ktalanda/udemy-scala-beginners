package lectures.part2oop

import playground.{PrinceCharming, Cindarella as Princess}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImport extends App {

  // package object
  sayHello
  println(SPPED_OF_LIGHT)

  //imports
  val prince = new PrinceCharming
  val princess = new Princess

  // 1. use FQ names
  val date = new Date()
  val sqlDate = new SqlDate(1L)
}
