//obtener un valor numerico aleatorio (Random.getInt), pedir por consola un numero
//si el numero introducido es mayor que el calculado, escribimos por consola "Es mayor" y volvemos a pedir otro numero
//en caso de ser menor, mismo resultado, pero por consola un "Es menor" y volveria a pedir infinitamente hasta acertar
//Cuando acierte, escribimos "Has acertado" y se termina el programa

import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.Random

object MainApp extends App {
  val nRNG = Random.nextInt(100)
@tailrec
def loop: Unit = {
  println("Piensa en un digito del 0 al 100")
  val num1 = StdIn.readInt()
  if (num1 == nRNG) {
    println("Has acertado!")
  }
  else if (num1 < nRNG) {
    println("Te has quedado corto")
    loop
  }
  else {
    println("Es posible que te hayas pasado")
    loop
  }
}
  loop
println (s"$nRNG era el numero")
}