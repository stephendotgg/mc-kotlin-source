package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerToggleSneak(e: PlayerToggleSneakEvent) {
        e.player.playSound(e.player.location, Sound.ENTITY_PLAYER_HURT, 1F, 1F)
//      Bukkit.getWorld("world")?.playSound(e.player.location, Sound.ENTITY_PLAYER_HURT, 1F, 1F)
    }

}