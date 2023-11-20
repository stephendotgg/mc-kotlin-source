package gg.stephen.test

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class ToggleChatListener : Listener {

    private var chatEnabled = true

    @EventHandler
    fun onPlayerInteract(e: PlayerInteractEvent) {
        if (e.hand != EquipmentSlot.HAND) return
        if (e.material != Material.SPONGE) return

        chatEnabled = !chatEnabled
        e.player.sendMessage(Component.text("You have ${if (chatEnabled) "enabled" else "disabled"} the chat!").color(NamedTextColor.BLUE))
    }

    @EventHandler
    fun onAsyncChat(e: AsyncChatEvent) {
        e.isCancelled = !chatEnabled
    }

}