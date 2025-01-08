package gg.stephen.test.team

import gg.stephen.test.manager.ArenaManager
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class TeamMenuListener : Listener {

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.holder !is TeamGUI) return

        val item = e.currentItem ?: return
        val player = e.whoClicked as Player
        val team = Team.valueOf(item.itemMeta.localizedName)

        ArenaManager.find(player)?.teams?.set(player, team)
        player.sendMessage(Component.text("You joined ").append(team.display))

        e.isCancelled = true
        player.closeInventory()
    }

}