package gg.stephen.test

import org.bukkit.plugin.java.JavaPlugin

class Tip4 : JavaPlugin() {

// BAD
override fun onEnable() {
val test = 5
repeat(test) {
println("test")
}
}

    // GOOD
    override fun onEnable() {
        val test = 5
        repeat(test) {
            println("test")
        }
    }

    override fun onEnable() {
        // BAD
        if (something) {
            if (something) {
                if (something) {
                    if (something) {

                    }
                } else {

                }
            }
        }

        // GOOD
        if (!something) return
        if (!something) return
        // ...
    }

}