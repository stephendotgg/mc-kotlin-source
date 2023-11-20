package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        getCommand("vanish")!!.setExecutor(VanishCommand())
        Bukkit.getPluginManager().registerEvents(VanishListener(), this)
    }

}