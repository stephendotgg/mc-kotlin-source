package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        Bukkit.getWorld("world")?.let {
            val armorStand = it.spawnEntity(Location(it, -276.0, 64.0, 2.0), EntityType.ARMOR_STAND) as ArmorStand
            armorStand.isSmall = true
        }
    }

    @EventHandler
    fun onPlayerItemConsume(e: PlayerItemConsumeEvent) {
        val player = e.player
        player.sendPlainMessage("You ate ${e.item.type.name}!")
        player.teleport(player.location.add(0.0, 5.0, 0.0))
    }

}