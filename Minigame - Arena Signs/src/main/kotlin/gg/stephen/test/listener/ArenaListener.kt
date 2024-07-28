package gg.stephen.test.listener

import gg.stephen.test.manager.ArenaManager
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class ArenaListener : Listener {

    @EventHandler
    fun onPlayerInteract(e: PlayerInteractEvent) {
        if (!e.hasBlock()) return
        if (e.hand != EquipmentSlot.HAND) return
        if (e.clickedBlock!!.type != Material.BIRCH_WALL_SIGN) return

        ArenaManager.findBySign(e.clickedBlock!!.location)?.let {
            Bukkit.dispatchCommand(e.player, "arena join ${it.id}")
        }
    }

}