import scala.None
import scala.io.StdIn

object Ejercicio2 extends App {
  sealed trait Pronoun

  case object Mister extends Pronoun
  case object Miss extends Pronoun
  case object Nonbinary extends Pronoun

  //primero, solicitar y reconocer lo introducido, poder distinguirlo

  println(
    "Ponga usted como si nos dirigimos de usted o en caso de que no, no ponga na"
  )

  val genero = StdIn.readLine()
  val pronombre =
    if (genero.isEmpty)
      Some(Nonbinary)
    else if (genero == "Sra")
      Some(Miss)
    else if (genero == "Sr")
      Some(Mister)
    else
      None

  println("Digame usted, esta casado o no? Responda con true o false")

  val casado = StdIn.readBoolean()

  println("Digame su nombre,desustanciao")
  val nombre = StdIn.readLine()
    if (nombre.forall(char => char.isLetter)) {
      val ending = pronombre map {
        case Nonbinary        => ""
        case Mister if casado => "Señor"
        case Miss if casado   => "Señor"
        case Mister           => "Señorito"
        case Miss             => "Señorita"
      }
      ending match {
        case Some(pronoun) => println(s"Buenos dias $pronoun $nombre")
        case None => println("Error")
      }
    } else
      println("Error")
  //AQui ya deberia poder funcionar y colocar los nombres


}
