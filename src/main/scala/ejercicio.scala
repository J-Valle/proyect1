import scala.Option
import scala.io.StdIn
import scala.util.Either
//Pedir por consola un texto,
//si el texto resulta ser un numero escribir por consola Hello World tantas veces como sea el numero
//si el texto no es un numero escribimos por consola Hello mas el texto introducido
//No se pueden usar If else
//Consejo: Se puede pasar de Option a Either con el metodo toEither




object Ejercicio extends App {
println("Escribe y veamos tu destino")

  val escrito = StdIn.readLine()
  val aloha=escrito.toIntOption.toLeft(escrito)

  aloha match {
    case _:Right[Int,String]=> println(s"Hello $escrito")
    case Left(int) => println("Hello world\n" * int)
  }






}
