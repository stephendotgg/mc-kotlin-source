package gg.stephen.test

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PermsListener : Listener {

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        PermsCommand.permissions.remove(e.player)
    }

}