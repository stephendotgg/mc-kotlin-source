package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onProjectileHit(e: ProjectileHitEvent) {
        val block = e.hitBlock ?: return
        val player = e.entity.shooter as? Player ?: return

        player.sendBlockChange(block.location, Material.REDSTONE_BLOCK.createBlockData())
    }

}