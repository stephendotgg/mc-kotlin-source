package gg.stephen.test.kit

import gg.stephen.test.manager.ArenaManager
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class KitMenuListener : Listener {

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.holder !is KitGUI) return

        val item = e.currentItem ?: return
        val player = e.whoClicked as Player
        val kitType = KitType.valueOf(item.itemMeta.localizedName)

        ArenaManager.find(player)?.setKit(player, kitType)
        player.sendMessage(Component.text("You equipped the kit: ").append(kitType.display))

        e.isCancelled = true
        player.closeInventory()
    }

}