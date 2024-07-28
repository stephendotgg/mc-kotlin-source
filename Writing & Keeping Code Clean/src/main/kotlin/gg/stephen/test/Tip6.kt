package gg.stephen.test

import org.bukkit.plugin.java.JavaPlugin

class Tip6 : JavaPlugin() {

    override fun onEnable() {
        val company = "Microsoft"
        println(if (company == "Microsoft") "Minecraft" else "System error!")

        var product: String
        if (company == "Microsoft") {
            product = "Minecraft"
        } else {
            product = "System error!"
        }
        println(product)
    }

}