package gg.stephen.test.listener

import gg.stephen.test.manager.ArenaManager
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.inventory.EquipmentSlot

class ArenaListener : Listener {

    @EventHandler
    fun onPlayerInteractAtEntity(e: PlayerInteractAtEntityEvent) {
        if (e.hand != EquipmentSlot.HAND) return
        if (e.rightClicked.type != EntityType.VILLAGER) return

        ArenaManager.findByVillager(e.rightClicked as Villager)?.let {
            Bukkit.dispatchCommand(e.player, "arena join ${it.id}")
        }
    }

}