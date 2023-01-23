import java.time.LocalDate

class SnowMan(val ID: Int, var name: String, var hasTopHat: Boolean, var dateOfBirth: LocalDate, var weight: Float){
    override fun toString(): String{
        return "ID: " + ID + " Name: " + name + " Has Hat: " + hasTopHat + " Birth: " + dateOfBirth + " Weight: " + weight
    }
}

fun main(args: Array<String>) {
    println("Welcome to snowman manager.")

    val man1 = SnowMan(1, "Frosty", false, LocalDate.parse("1952-12-25"), 88.1f)

    //create a new list and add Frosty as the default starter
    var snowMenList = mutableListOf<SnowMan>()
    snowMenList.add(man1)

    var userChoice = 0;
    var c = 1


    while (userChoice != 9){

        println("Please choose an option: ")
        println("1.\tAdd a new snowman to the inventory\n" +
                "2.\tDisplay all snowmen in the inventory\n" +
                "3.\tShow a single snowman given its id number\n" +
                "4.\tSearch for a snowman by (partial) name\n" +
                "5.\tDelete a snowman\n" +
                "6.\tChange a snowman\n" +
                "9.\tQuit the program\n")

        userChoice = Integer.parseInt(readLine())
        when (userChoice){
            1 -> {

                println("What name do you want the snoman to have?")
                val user_name = readLine()!!

                println("Want them to get a cool hat? [Y/N]")
                val user_hat = readLine()!!
                var isHat = false
                if(user_hat == "Y"){
                    isHat = true
                }

                println("What's the date of 'birth' for the snowman? [YYYY-MM-DD]")
                val date_of_birth = readLine()!!

                println("How much should they weight?")
                val user_weight = readLine()!!
                var weight_float = user_weight.toFloat()

                c++
                val tempMan = SnowMan(c, user_name, isHat, LocalDate.parse(date_of_birth), weight_float)
                snowMenList.add(tempMan)


            }
            2 -> {

                println("Here's all the snowmen we got: ")
                snowMenList.forEach({
                    println("Here is a snowman $it ")
                })
                println("")

            }
            3 -> {

                println("What's the ID of the snowman you wanna find?")
                var IDChoice = Integer.parseInt(readLine())
                var found = false
                snowMenList.forEach{
                        if(it.ID == IDChoice){
                            println("Here they are: $it")
                            found = true
                    }
                }
                if(found == false){

                    println("Didn't find one with that ID, sorry.\n")

                }

            }
            4 -> {

                println("What's the name of the snowman you're trying to find?")
                var nameChoice = readLine()!!
                var found_name = false
                snowMenList.forEach{
                    if(it.name == nameChoice){
                        println("Here they are: $it")
                        found_name = true
                    }
                }
                if(found_name == false){

                    println("Didn't find one with that name, sorry.")

                }
                println("")

            }
            5 -> {
                
                println("Give the ID of the snowman you want... 'out of the way':")
                var user_ID = Integer.parseInt(readLine())
                var remove = false

                snowMenList.forEach{
                    if(it.ID == user_ID){
                        remove = true
                    }
                }
                if(remove == true) {
                    snowMenList.removeAt(user_ID - 1)
                }else{
                    println("No one has that ID.")
                }
                println("")

            }
            6 -> {
        
                println("Enter the ID of the snowman you want to modify:")
                var user_ID = Integer.parseInt(readLine())
                println("What do you want to change?")
                println("[1] Name")
                println("[2] Hat Status")
                println("[3] Date of birth")
                println("[4] Weight")

                var user_choice = Integer.parseInt(readLine())
                println("What do you want to change it to?")
                var new_choice = readLine()!!
                snowMenList.forEach{
                    if(it.ID == user_ID){
                        if(user_choice == 1){
                            it.name = new_choice
                        }else if(user_choice == 2){
                            if(new_choice == "Y"){
                                it.hasTopHat = true
                            }else{
                                it.hasTopHat = false
                            }
                        }else if(user_choice == 3){
                            it.dateOfBirth = LocalDate.parse(new_choice)
                        }else if(user_choice == 4){
                            it.weight = new_choice.toFloat()
                        }
                    }
                }
                println("")
            }
            9 -> {
                /*
                
                Nothing to put here, program is done.

                */
            }
        }
    }
}