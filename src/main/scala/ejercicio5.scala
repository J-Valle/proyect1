import scala.io.StdIn
import scala.util.Try

//Write a menu to manage users, users will only have a user name and their age.
//The menu has five options:
//1. Insert a user
//2. Update user age
//3. Delete a user
//4. Show users
//5. Exit program

//The program user will introduce the option number to navigate through the menu
//In case the program user introduce something not valid when navigating through the menu
//like numbers < 1 or > 5 or text
//we have to capture the exception and ask them for re"try"
//after doing the proper operation depending of the user selected option
//we will show the menu again asking for the following option

object ejercicio5 extends App {
  case class NewUser(name: String, age: Int)
  val sergio = NewUser("Sergio", 31)
  val sofia = NewUser("Sofia", 53)
  var userList: List[NewUser] = List(sergio, sofia)

  def aplicacion: Unit = {
    println(
      """Please, press a number to access a function, dooderino.
        |1. Insert a user
        |2. Update user name
        |3. Delete user
        |4. Show users
        |5. Exit program""".stripMargin
    )
    //preparar a la funcion para excepciones inesperadas y colocar un try
    val menuoption = Try(StdIn.readInt())
    //Con ya el numero introducido, hay que poner que hacer en caso de, tanto que coloque bien, o falle.
    if (menuoption.isSuccess)
      whensuccess(menuoption.get)
    else {
      println("Error, invalid input.")
      aplicacion
    }
  }
  def whensuccess(menuoption: Int) = {
    // mirar posiblidad de en lugar de map poder hacer cases de usuarios
    if (menuoption == 1) {
      println("Please, write the user.")
      val newusername = StdIn.readLine()
      println("Now its age.")
      val newuserage = StdIn.readInt()
      val userAdded = NewUser(newusername, newuserage)
      if (userList.exists(userinlist => userinlist.name == newusername)) {
        println("User already logged, coming back to main menu")
      } else {
        userList = userList :+ userAdded
        println("User added.")
      }

      aplicacion
    } else if (menuoption == 2) {

      println(s"""
         |What name do you want to change/update?
         | $userList""".stripMargin)
      val userChangeName = StdIn.readLine()
      if (userList.exists(userinlist => userinlist.name == userChangeName)) {
        println("Insert new age")
        val newChangedUserage = StdIn.readInt()
        val userUpdated = NewUser(userChangeName, newChangedUserage)
        val userSearch =
          userList.indexWhere(userList => userList.name == userChangeName)
        userList = userList.updated(userSearch, userUpdated)
        println("User updated.")
      } else {
        println("Name not found, coming back to main menu.")
      }

      aplicacion

    } else if (menuoption == 3) {
      println(s"""
           |Please, introduce the user you want to delete.
           | $userList""".stripMargin)
      val userdeletedname = StdIn.readLine()
      println("Now it's age to confirm.")
      val userdeletedage = StdIn.readInt()
      val userdeleted = NewUser(userdeletedname, userdeletedage)
      if (userList.contains(userdeleted)) {
        userList = userList.filter(_ != userdeleted)
        println("User deleted.")
      } else {
        println("User not found, coming back to main menu.")
      }
      aplicacion

    } else if (menuoption == 4) {
      println("Here's the list of all current users.")
      println(s"$userList")

      aplicacion

    } else if (menuoption == 5) {
      println("Thanks for using our services, dooderonni.")

    } else if (menuoption == 6) {
      println(
        "Scala da ELO, Scala da dinero, Scala todo lo puede sisisiisisisisi"
      )

      println("\n" * 20)
      aplicacion

    } else if (menuoption > 0 | menuoption < 7) {
      println("Invalid number, try again.")

      aplicacion
    }

  }
  aplicacion

}
//En caso de que no sea una opcion del menu, repetir la aplicacion con un mensaje de error avisando del fallo
//
//
