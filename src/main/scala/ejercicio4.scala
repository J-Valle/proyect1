import scala.io.StdIn
//Vamos a hacer un juego llamado "Buscapalabras", vamos a introducir la palabra que queremos que adivinen, luego
//como mensaje inicial se va a escribir "_" separadas por espacios, tantas como letras tenga la palabra.
//Pedimos una letra, si estan sustituimos las barras bajas con la letra introducida y si no hay ninguna, decimos que no hay
//nada (cagada). Una vez que adivine todas las letras, se printea el mensaje de finalizacion con la palabra final mencionada.

object Ejercicio4 extends App {
//Pedir la palabra del juego
  println("Jugador 1, ponga la palabra a adivinar.")
  val palabra = StdIn.readLine().toLowerCase
  //Convertimos la palabra en una lista
  val lista1 = palabra.toList
  //A partir de dicha lista, se crea otra de barras bajas con la misma longitud

  println("\n" * 20)
  //ENcontrar un metodo para ignorar los espacios en caso de ser introducidos en lista1 (palabra)
  val barrabaja = lista1.map(letra =>
    if (letra == ' ') {
      ' '
    } else {
      '_'
    }
  )

  // Mostrar la palabra con barras bajas
  println(barrabaja.mkString(" "))
  //Convertir la lista con la palabra en una lista en la que cada letra tiene su posicion en la misma
  val orden = lista1.zipWithIndex

  // En un loop que recibe la lista actualizada de barras bajas
  def loop(juego: List[Char]): Unit = {
    // Pedir la letra por consola
    println("Introduce la letra para adivinar la palabra")
    val adivinar = StdIn.readChar().toLower
    //La lista obtenida con posiciones, comprueba la letra introducida con las de la lista
    val positions = if (lista1.contains(adivinar)) {
      //En caso de que si, devuelve una lista con las posiciones a cambiar segun la letra introducida
      orden
        .filter { case (letra, _) =>
          adivinar == letra
        }
        .map(tupla => tupla._2)
    }
    //En caso de que no, se devuelve una lista vacia para no modificar ninguna barra baja
    else {
      println("Nope, intenta otra letra.")
      List()
    }
    //Ahora usamos la lista de posiciones que tenemos que sustituir para cambiar las barras por la letra introducida en
    // dichas posiciones
    //para cada posicion en la lista de posiciones tenemos que cambiar la barra baja en esa posicion en la lista de barras
    //bajas por la letra introducida al principio (adivinar)
    val listamedia = positions.foldLeft(juego) {
      case (barrabajaAcc, position) => barrabajaAcc.updated(position, adivinar)
    }
    println(listamedia.mkString(" "))

    //Condicion de fin: Lista de barras bajas == Lista de palabra inicial
    if (listamedia == lista1) {}

    //Y si no lo es, itera en el loop
    else {
      loop(listamedia)
    }
    //Fin de loop
  }
  // ESta es una llamada al metodo que hemos creado arriba, no se puede ejecutar antes de ser creado
  loop(barrabaja)
  //Print palabra celebratoria y revelando la palabra original
  println(s"Enhorabuena, la palabra era $palabra")

}
