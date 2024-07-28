package gg.stephen.test

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class ConversationListener : Listener {

    @EventHandler
    fun onAsyncChat(e: AsyncChatEvent) {
        val player = e.player
        if (!ConversationManager.conversations.containsKey(player)) return

        e.isCancelled = true
        player.sendMessage(Component.text("You: ").append(e.message()))

        player.sendMessage(Component.text("AI:").append(ConversationManager.getResponse(player, e.message())))
    }

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        ConversationManager.conversations.remove(e.player)
    }

}