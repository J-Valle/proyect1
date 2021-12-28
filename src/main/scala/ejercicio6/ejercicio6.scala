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
  val autor = Console[Option].readString
  // def inicial para el ejercicio del triangulo
  def beggining: Unit = {

    println("Define la altura del triangulico")
    val num1 = Console[Try].readInt
    //-----------------
    //if (num1.isSuccess)
    //loop(0, num1.get)
    //else {
    //beggining
    //}
    //-----------------
    //match con caso de success y failure para el try solicitado anterior
    //Success en caso de que num1 sea un numero, failure lo devuelve al principio
    num1 match {
      case Success(num1) => loop(0, num1)
      case Failure(_)    => beggining
    }
  }
  //el tailrec es una anotacion para mantener la recursividad optimizada, avisandome si deja de estarlo
  @tailrec
  //Al haber colocado num1 como segunda parte de loop, se crea una conexion entre ambos pudiendo usarlo en ambos
  //
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
//patern matching con some y none del option usado para el autor, la exclamacion avisa de que se busca lo contrario de
  //lo escrito en la formula, en este caso: se busca si el dato introducido NO esta en blanco
  autor match {
    case Some(autorname) if !autorname.isBlank =>
      println(s"Created by $autorname")
    case _ => ()
  }

  println("Aqui tienes tu magnifica piramide, tusinzuelinillo")
  //----
  //def para el caso de la despedida
  def goodbye: Unit = {
    println("Adios mi querido.... ¿Quien eras?")
    val pathway = Console[Option].readString
    //patern matching para el option solicitado
    pathway match {
      //primer caso, miramos si el dato introducido, el cual se convierte a int, no este vacio
      case Some(firstPath) if firstPath.toIntOption.nonEmpty =>
        //se convierte el resultado final a Int para poder introducirlo correctamente
        println("Adios " * firstPath.toInt)
        //en este caso, de nuevo, exclamacion para denotar lo contrario, se busca si existe en cualquiera de los
      //caracteres que hay uno solo que sea un numero, osease, que lo acepte si no tiene ningun numero
      case Some(secondPath) if !secondPath.exists(letter => letter.isDigit) =>
        println(s"Adios $secondPath.")
      case _ =>
        println("¿Pero que me ah dixo?")
        goodbye
    }
  }
  goodbye
}
