package ejercicio6

import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

// Definir dos implementaciones del wrapper, una para option y otra para try
// Ejercicio del triangulo usando el wrapper en vez de los StdIn
// Pedir por consola el nombre del autor si esta vacio (None) y sino (Some) dependiendo de si tenemos
// el nombre o no, pondremos despues de escribir el triangulo "Created by Autor"
// Pedir por consola un texto, si el texto es numerico (full Int) se le dice Adios tantas veces como nº introducido,
// si es texto, pues le decimos adios al nombre introducido como texto.

object Triangulo extends App {
  println("¿Quien va a crear esta magnificencia?")
  val autor = ConsoleOption.readString
  def beggining: Unit = {

    println("Define la altura del triangulico")
    val num1 = ConsoleTry.readInt
    //-----------------
    //if (num1.isSuccess)
    //loop(0, num1.get)
    //else {
    //beggining
    //}
    //-----------------
    num1 match {
      case Success(num1) => loop(0, num1)
      case Failure(_)    => beggining
    }
  }
  @tailrec
  def loop(seguir: Int, numberino: Int): Unit = {
    if (seguir != numberino) {
      val sequencia = seguir + 1
      val espacios = List.fill(numberino - sequencia)(" ")
      val asteriscos = List.fill(sequencia)("*")
      println(asteriscos.mkString(espacios.mkString, " ", ""))
      loop(sequencia, numberino)
    } else ()
  }
  beggining

  autor match {
    case Some(autorname) if !autorname.isBlank =>
      println(s"Created by $autorname")
    case _ => ()
  }

  println("Aqui tienes tu magnifica piramide, tusinzuelinillo")
  //----
  def goodbye: Unit = {
    println("Adios mi querido.... ¿Quien eras?")
    val pathway = ConsoleOption.readString
    pathway match {
      case Some(firstPath) if firstPath.toIntOption.nonEmpty =>
        println("Adios " * firstPath.toInt)
      case Some(secondPath) if !secondPath.exists(letter => letter.isDigit) =>
        println(s"Adios $secondPath.")
      case _ =>
        println("¿Pero que me ah dixo?")
        goodbye
    }
    //Coger el string del option y ver si es un int, si lo es, va a un lado del either, sino al otro. En base a ello se
    //hara una lagica u otra
  }
  goodbye
}
