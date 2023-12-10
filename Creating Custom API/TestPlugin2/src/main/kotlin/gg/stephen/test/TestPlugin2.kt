package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin2 : JavaPlugin() {

    override fun onEnable() {
        (Bukkit.getPluginManager().getPlugin("TestPlugin") as TestPlugin).run {
            println(getWord())
            broadcast()
        }
    }

}