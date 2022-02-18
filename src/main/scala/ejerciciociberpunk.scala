import cats.effect.{ExitCode, IO, IOApp, Sync}
import cats.effect.std.Console
import cyberpunk.escritoLanzamiento

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import scala.annotation.tailrec
import scala.io.StdIn
//Crea un programa que reciba la fecha de lanzamiento del Cyberpunk 2077 por consola y
//cogiendo la fecha actual, itere por dia. Dos semanas antes del lanzamiento
//debe publicar un comunicado diciendo que se retrasa 20 dias.
//La funcion para iterar sobre la fecha actual debe ser recursiva y
//una vez retrasada la fecha de lanzamiento debe seguir iterando
//hasta 4 veces con las correspondientes fechas de lanzamiento.
//Para acabar despues de los 4 retrasos debe publicar un comunicado celebrando el lanzamiento.
//La fecha de lanzamiento introducida debe usar IO para controlar posibles problemas
//
//b. Modificar el programa anterior para que dos semanas y
//  un dia antes del lanzamiento publicar otro mensaje confirmando esa fecha de salida.
//
//c. Modificar el programa una vez mas para que sea capaz de iterar infinitamente.
//  Al terminar una iteracion debe preguntarte si deseas cancelar o conservar la reserva del juego.

//Note: Todos los comunicados deben llevar la fecha en la que se estan publicando

//Hint: Puedes usar la anotacion @tailrec para asegurarte de que tu recursividad esta optimizada.

object cyberpunk extends IOApp {
  @tailrec
  //Metodo para controlar los dias que van pasando, usando LocalDate para controlar tanto la entrada como la salida por separado
  //Ademas de tener un valor para los delays comenzando en 0, tambien hace saber que su resultado final sera un string
  private def controlDeFechas(lanzamiento: LocalDate, retraso: LocalDate, repeticiones: Int = 0): Unit = {
    println(s"Día $lanzamiento")
    //Valor para establecer los valores separados de dia y salida por separado y prepararlos para el if
    val planeodiario: Long = ChronoUnit.DAYS.between(lanzamiento, retraso)
    //If que en caso de que no queden dias y el delay sea mayor o igual a 4
    if (planeodiario == 0 && repeticiones >= 4) {
      println("¡Por fin carajo!")

    }
    //En caso de que los dias restantes sean menores a 15 y no hayan pasado mas de 4 delays
    else if (planeodiario < 15 && repeticiones <= 3) {
      //print
      println("Adivina, otro retraso mas.")
      //Y se añaden 20 dias mas
      val otraVez = retraso.plusDays(20)
      //print con la nueva fecha
      println(s"Pues ahora tocara esperar hasta el $retraso")
      controlDeFechas(lanzamiento.plusDays(1), otraVez, repeticiones + 1)

    }
    //Se añade 1 al contador de dias iniciales, cambia la fecha con la nueva release y tambien se añade al delay

    //Otro else para el caso de no haber añadido el delay, simplemente se avanza el dia, manteniendo el valor de la release
    else {
      controlDeFechas(lanzamiento.plusDays(1), retraso, repeticiones)
    }
  }
  def escritoLanzamiento: IO[Unit] =
    (for {
      _ <- IO.delay(println("Dime que dia crees que saldra esta joya con el sensualicioso Keanu Reeves, si es posible con formato yyyy-mm-dd"))
      fechaLanzamiento <- IO.delay(LocalDate.parse(StdIn.readLine()))
      _ <- if (fechaLanzamiento.compareTo(LocalDate.now()) < 0 ){
        IO.delay(println("Pon una fecha actual por favor.")) >> escritoLanzamiento
      }
      else{
        IO.unit
      }
    }yield controlDeFechas(
      LocalDate.now(),
      fechaLanzamiento,
      0
    )).handleErrorWith(
      _ => escritoLanzamiento)



  override def run(args: List[String]): IO[ExitCode] = {
    escritoLanzamiento.as(ExitCode.Success)
  }
}

