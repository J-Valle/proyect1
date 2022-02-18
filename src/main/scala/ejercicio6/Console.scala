package ejercicio6

import scala.io.StdIn
import scala.util.Try

//wrapper para el ejercicio 6
//Los wrappers sirven para encapsular o envolver un comportamiento y adaptarlo a las necesidades de un programa
trait Console[F[_]] {
  def readBoolean: F[Boolean]
  def readInt: F[Int]
  def readString: F[String]
}
//pasar de implicit object a implicit value

object Console {
  //crear metodo apply
  def apply[F[_]](implicit c: Console[F]): Console[F] = c

  implicit val ConsoleTry  = new Console[Try] {

    override def readBoolean: Try[Boolean] = Try(StdIn.readBoolean())

    override def readInt: Try[Int] = Try(StdIn.readInt())

    override def readString: Try[String] = Try(StdIn.readLine())
  }

  //Option seria para poder ignorar un caso de error

  implicit val ConsoleOption = new Console[Option] {

    override def readBoolean: Option[Boolean] = Try(
      StdIn.readBoolean()
    ).toOption

    override def readInt: Option[Int] = Try(StdIn.readInt()).toOption

    override def readString: Option[String] = Try(StdIn.readLine()).toOption
  }

}
