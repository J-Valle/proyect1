import scala.None
import scala.io.StdIn
//Escribir un programa que pida el pronombre, que puede ser sr, sra. Sl el usuario lo deja vacio/no mete na
//lo interpretamos como genero no binario. Tendremos que validar que el usuario solo introduce una de las dos
// posibilidades o no introduce nada. Luego le preguntaremos si esta casado, donde tendra que introducir true o false
//SI no lo esta, añadiremos al pronombre un "ito/ita". POr ultimo, preguntaremos su nombre, que no puede estar vacio,
//Ni contener numeros/caracteres especiales y le daremos los buenos dias, usando su pronombre y el nombre
//Nota: Recordar advertir al usuario cuando introduce el pronombre de la posibilidad de no poner nada

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
//Casos: Sr. // Sra. // vacio (nb) //error
  val pronombre =
    if (genero.isEmpty)
      Some(Nonbinary)
    else if (genero == "Sra")
      Some(Miss)
    else if (genero == "Sr")
      Some(Mister)
    else
      None

  // introducir el boolean por casado o no y sus consecuencias (sisisisisisisisisisisisi)

  println("Digame usted, esta casado o no? Responda con true o false")

  val casado = StdIn.readBoolean()

  //Preguntar el nombre y tratar de poner excepciones en caso de que haya algo raro
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

  //Una vez obtenido todo, poder colocar los "flags" para añadir todo lo dicho


}
