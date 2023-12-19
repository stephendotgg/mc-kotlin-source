package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class ConnectionListener : Listener {

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        e.player.inventory.addItem(ItemStack(Material.IRON_PICKAXE).apply {
            addUnsafeEnchantment(Enchantment.getByKey(AutoSmeltingEnchantment.KEY)!!, 1)
            val meta = itemMeta
            meta.displayName(Component.text("Super Pickaxe"))
            meta.lore(mutableListOf(Component.text("Auto Smelting I").color(NamedTextColor.GRAY)))
            itemMeta = meta
        })
    }

}