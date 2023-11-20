package gg.stephen.test

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class VanishListener : Listener {

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        VanishCommand.vanished.remove(e.player)
    }

}