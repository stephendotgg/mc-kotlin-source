package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.entity.Firework
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        e.player.world.spawn(e.player.location.add(0.0, 2.0, 0.0), Firework::class.java).apply {
            val meta = fireworkMeta
            meta.addEffect(FireworkEffect.builder()
                .withColor(Color.RED)
                .withColor(Color.BLUE)
                .withFlicker()
                .withTrail()
                .with(FireworkEffect.Type.CREEPER)
                .build())
            meta.power = 2
            fireworkMeta = meta
        }
    }

}