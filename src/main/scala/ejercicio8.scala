import cats.effect.{ExitCode, IO, IOApp}

import scala.io.StdIn

//Hacer mediante un "for comprehension" un programita que pida un numero y luego tras escribirlo lo printee de vuelta a
//la "Tu número es X"
//Como lo voy a hacer? No tengo ni puta idea, pero vamos a ello, este es el ultimo puto paso

object ejercicio8 extends IOApp{
  
println("Escribe cualquier número")

  override def run(args: List[String]): IO[ExitCode] =
  for{
    a <- IO.delay(StdIn.readInt())
    _ = println(s"Tu número es $a")
  } yield ExitCode.Success
}
