import cats.implicits.toTraverseOps

import scala.util.Try

object ejercicio9 {
  val notAllNumbers = List("1", "a", "3")
  notAllNumbers.map(str => Try(str.toInt).toOption) // List[Option[Int]]
  notAllNumbers.traverse(str => Try(str.toInt).toOption) // Option[List[Int]]

  val list = List(Some(1), Some(2), None)

  list.traverse(identity)
  list.sequence

  notAllNumbers.foldLeft[Option[List[Int]]](None) {
    case (acc, elem) => Try(List(elem.toInt)).toOption
  }
}
  //Implement traverse using foldLeft for notAllNumbers
  //it should return Some(List(1, 3))
