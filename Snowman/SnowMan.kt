import java.time.LocalDate

class SnowMan(val ID: Int, var name: String, var hasTopHat: Boolean, var dateOfBirth: LocalDate, var weight: Float){
    override fun toString(): String{
        return "ID: " + ID + " Name: " + name + " Has Hat: " + hasTopHat + " Birth: " + dateOfBirth + " Weight: " + weight
    }
}