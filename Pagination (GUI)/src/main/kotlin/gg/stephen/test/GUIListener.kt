package gg.stephen.test

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class GUIListener : Listener {

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        val warpsGUI = e.inventory.holder as? WarpsGUI ?: return
        val item = e.currentItem ?: return

        when (e.rawSlot) {
            0 -> {
                if (item.type == Material.LIME_STAINED_GLASS_PANE) {
                    warpsGUI.page--
                    warpsGUI.update()
                }
            }
            8 -> {
                if (item.type == Material.LIME_STAINED_GLASS_PANE) {
                    warpsGUI.page++
                    warpsGUI.update()
                }
            }
            else -> {
                e.whoClicked.sendMessage(Component.text("You clicked on a Warp! This feature does not yet exist. Sorry!"))
            }
        }

        e.isCancelled = true
    }

}