package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerEggThrowEvent
import org.bukkit.event.player.PlayerMoveEvent

class TestListener : Listener {

    @EventHandler
    fun onPlayerMove(e: PlayerMoveEvent) {
        e.isCancelled = true
    }

    @EventHandler
    fun onPlayerEggThrow(e: PlayerEggThrowEvent) {
        e.player.sendMessage(Component.text("You threw an egg!").color(NamedTextColor.GREEN))
    }

}