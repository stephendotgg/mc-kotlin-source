package gg.stephen.test

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class ServerSwitcherListener(private val testPlugin: TestPlugin) : Listener {

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.holder !is ServerSwitcherGUI) return
        if (e.currentItem == null) return

        e.isCancelled = true

        (e.whoClicked as Player).sendPluginMessage(
            testPlugin,
            "custom:main",
            "switch|${PlainTextComponentSerializer.plainText().serialize(e.currentItem!!.itemMeta.displayName()!!)}".toByteArray())
    }

}