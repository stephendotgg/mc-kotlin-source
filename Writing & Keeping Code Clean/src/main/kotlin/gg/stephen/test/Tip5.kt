package gg.stephen.test

import org.bukkit.plugin.java.JavaPlugin

class Tip5 : JavaPlugin() {

    // BAD
    fun checkEmployeeDetails(name: String, age: Int, awards: Array<String>, jobTitle: String, yearsExperience: Int, promotion: Boolean) {

    }

    // GOOD
    fun checkEmployeeDetails(details: EmployeeDetails) {

    }

    data class EmployeeDetails(val name: String,
                               val age: Int,
                               val awards: Array<String>,
                               val jobTitle: String,
                               val yearsExperience: Int,
                               val promotion: Boolean)

}