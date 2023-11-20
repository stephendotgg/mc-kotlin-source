package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        val block = Bukkit.getWorld("world")!!.getBlockAt(0, 0, 0)
        block.type = Material.SPONGE
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        e.player.inventory.addItem(ItemStack(Material.GLASS_BOTTLE, 32).apply {
            val meta = itemMeta
            meta.displayName(Component.text("My cool item!").color(NamedTextColor.RED))
            meta.lore(listOf(
                Component.text("Line 1!").decorate(TextDecoration.BOLD),
                Component.empty()
            ))
            itemMeta = meta
        })
    }

}