package gg.stephen.test

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class ConnectionListener : Listener {

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        NametagManager.setNametags(e.player)
        NametagManager.newNametag(e.player)
    }

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        NametagManager.removeNametag(e.player)
    }

}