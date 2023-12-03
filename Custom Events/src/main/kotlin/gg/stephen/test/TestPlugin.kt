package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        getCommand("broadcast")!!.setExecutor(BroadcastCommand())
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onServerBroadcast(e: ServerBroadcastEvent) {
        println("Please work!")
        println("Player name: ${e.player.name}")
        println("Message: ${e.message}")
    }

}