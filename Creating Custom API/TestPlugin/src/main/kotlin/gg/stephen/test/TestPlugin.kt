package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {

    }

    fun getWord(): String {
        return "banana"
    }

    fun broadcast() {
        Bukkit.broadcast(Component.text("Broadcast worked!"))
    }

    private fun superDuperSecret() {
        Bukkit.broadcast(Component.text("Uh oh!!!").color(NamedTextColor.RED))
    }

}