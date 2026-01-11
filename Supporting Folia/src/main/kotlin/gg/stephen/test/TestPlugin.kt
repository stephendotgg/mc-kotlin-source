package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getServer().globalRegionScheduler.run(this) {
            Bukkit.getWorld("world")?.time = 1000L
        }

        val location = Bukkit.getWorld("world")?.getBlockAt(-280, 64, 3)?.location
        Bukkit.getServer().regionScheduler.run(this, location!!) {
            location.block.type = Material.DIAMOND_BLOCK
        }

        Bukkit.getServer().asyncScheduler.runNow(this) {

        }

        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerInteract(e: PlayerInteractEntityEvent) {
        val entity = e.rightClicked
        entity.scheduler.runAtFixedRate(this, { task ->
            if (entity.isDead) {
                task.cancel()
                return@runAtFixedRate
            }

            entity.world.spawnParticle(
                Particle.CAMPFIRE_COSY_SMOKE,
                entity.location.add(0.0, 1.0, 0.0),
                10
            )
        }, null, 1L, 20L)
    }

}