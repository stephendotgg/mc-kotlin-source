package gg.stephen.test.listener

import gg.stephen.test.manager.ArenaManager
import gg.stephen.test.manager.ConfigManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class ConnectionListener : Listener {

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        e.player.teleport(ConfigManager.LOBBY_SPAWN)
    }

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        ArenaManager.find(e.player)?.removePlayer(e.player)
    }

}