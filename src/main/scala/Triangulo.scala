//Pedir un numero por consola y crear una piramide de asteriscos
//EJ: Si se introduce un 3, se crea un triangulo de 3 filas, y si se pide mas, se adapta
//   *
//  * *
// * * *
// Notas: OK, parece ser que se puede conseguir con recursividad segun Adri, para ello tengo en mente poner un valor
// inicial a 0 y que el numero insertado sean incrementos para la siguiente linea en la cual se añada "_*" por cada
// valor en referencia a la linea anterior, una a una, hasta que se llegue al valor puesto por el usuario

import scala.io.StdIn

object Triangulo extends App {
  println("Define la altura del triangulico")
  val num1 = StdIn.readInt()

  def loop(seguir: Int): Unit = {
    if (seguir != num1) {
      val sequencia = seguir + 1
      val espacios = List.fill(num1 - sequencia)(" ")
      val asteriscos = List.fill(sequencia)("*")
      println(asteriscos.mkString(espacios.mkString, " ", ""))
      loop(sequencia)
    } else ()
  }
  loop(0)
  println("Aqui tienes tu magnifica piramide, tusinzuelinillo")
}
