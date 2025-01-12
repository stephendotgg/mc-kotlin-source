package gg.stephen.test.listener

import gg.stephen.test.GameState
import gg.stephen.test.manager.ArenaManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class LobbyListener : Listener {

    @EventHandler
    fun onEntityDamage(e: EntityDamageEvent) {
        if (e.entity !is Player) return

        val arena = ArenaManager.find(e.entity as Player)
        if (arena == null || arena.state != GameState.LIVE) {
            e.isCancelled = true
        }
    }

}