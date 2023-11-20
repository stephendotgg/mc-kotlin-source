package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class MenuListener : Listener {

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.holder !is MenuGUI) return
        if (e.currentItem == null) return

        e.isCancelled = true

        val player = e.whoClicked as Player

        when (e.rawSlot) {
            0 -> Unit
            20 -> { // random teleport
                val target = Bukkit.getOnlinePlayers().random()
                player.teleport(target)
                player.sendPlainMessage("You teleported to ${target.name}.")
            }
            22 -> { // kill yourself
                player.health = 0.0
                player.sendPlainMessage("You killed yourself!")
            }
            24 -> { // empty inventory
                player.closeInventory()
                player.inventory.clear()
                player.sendPlainMessage("You emptied your inventory!")
            }
            else -> return
        }

        player.closeInventory()
    }

}