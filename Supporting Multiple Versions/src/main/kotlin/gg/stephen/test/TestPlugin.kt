package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    lateinit var hologram: Hologram

    override fun onEnable() {
        println(Bukkit.getVersion())

        if (Bukkit.getVersion().contains("1.9.4")) {
            hologram = Hologram1_9()
        }
        hologram.spawn()
    }

}