fun main(args: Array<String>){
    print("Enter your name: ")
    var name = readLine()
    print("\n")
    
    var attempts = 5
    var magic_num = (0..100).random()
    var guesses = mutableListOf<Int>()
    var is_playing = true

    while(attempts > 0 && is_playing == true){

        println("Make a guess, " + name + ", you have " + attempts + " remaining.")
        var guess = readLine()!!.toInt()

        guesses.add(guess)

        if(guess == magic_num){

            println("Great job " + name + ", you got it right! Here are your attempts:")
            for(x in guesses){

                println(x)

            }
            
            attempts = 5
            print("Wanna play again? [Y/N]: ")
            var answer = readLine()

            if(answer != "Y" && answer != "y"){ is_playing = false }


        } else{


            if(guess < magic_num){ println("Wrong! Too Low.") }
            if(guess > magic_num){ println("Wrong! Too high.") }
            attempts--

            if(attempts <= 0){

                println("Sorry " + name + ", game over. Here were your guesses:")
                for(x in guesses){

                    println(x)
    
                }
                println("The actual number was " + magic_num + ".")

                print("Wanna play again? [Y/N]: ")
                var answer = readLine()

                if(answer != "Y" && answer != "y"){ is_playing = false }
                attempts = 5

            }

        }

    }


}