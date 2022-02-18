import scala.io.StdIn
//añadir dos metodos al tipo string:
//uno que devuelva option de string siendo None el caso en el que la string este vacia o sean espacios
//el otro va a devolver un Either en caso de que el string sea un numero, lo convertira a Int y lo devolvera a la
//derecha, en cualquier otro caso devolvera el string original a la izquierda
//Pedir un texto por consola y usar los metodos añadidos al string y printear el resultado de cada metodo

object ejercicio7 extends App {
// clase implicita para agrupar los metodos aplicados a strings al ponerlo de parametro
  implicit class Elpis(ascian: String) {
  //primer metodo, devolviendo un either de string(left) e int(right)
    def hyloth = {
      // toIntOption para ver si es capaz de volverlo int y el toRight para en caso de ello, convertirlo en either y
      // ese valor en concreto sea el derecho(Right), en el parentesis ponemos la otra posibilidad (ascian que es string)
      ascian.toIntOption.toRight(ascian)

    }
  //segundo metodo, devolviendo un option de string, usando ascian para ello
    def emet = {
      //if para ver si esta vacio, la condicion del option
      if (!ascian.isBlank) {
        //al poner Some(ascian) se crea el option usando el string de ascian
        Some(ascian)
      } else {
        None
      }

    }

  }
  val etherys = StdIn.readLine()
  //aplicacion de los metodos sobre etherys una vez ya pedido el texto por consola
  println(etherys.hyloth)
  println(etherys.emet)
}
sealed trait CorteDeCarne
case object Tapa extends CorteDeCarne
case object Lomo extends CorteDeCarne
case object Pez extends CorteDeCarne
