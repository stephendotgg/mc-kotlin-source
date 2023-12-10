package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.MapInitializeEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onMapInitialize(e: MapInitializeEvent) {
        e.map.apply {
            renderers.forEach(::removeRenderer)
            addRenderer(CustomRenderer())
        }
    }

}