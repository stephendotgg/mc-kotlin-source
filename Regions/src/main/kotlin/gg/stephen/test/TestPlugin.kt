package gg.stephen.test

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    val region = Cuboid(-279, 64, 5, -275, 67, -1)

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        region.getBlocks(Bukkit.getWorld("world")!!).forEach {
            it.type = Material.SPONGE
        }
    }

    @EventHandler
    fun onPlayerMove(e: PlayerMoveEvent) {
        if (region.contains(e.player.location)) {
            e.player.sendActionBar(Component.text("Inside region!"))
        }
    }

}