package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class MenuGUI(player: Player) : InventoryHolder {

    private val inventory = Bukkit.createInventory(this, 45, Component.text("Tool Menu!").color(NamedTextColor.GREEN))

    init {
        inventory.apply {
            setItem(0, ItemStack(Material.BARRIER).apply {
                val meta = itemMeta
                meta.displayName(Component.text("Close menu!").color(NamedTextColor.RED))
                itemMeta = meta
            })

            val frame = ItemStack(Material.GREEN_STAINED_GLASS_PANE)
            arrayOf(1,2,3,4,5,6,7,8,9,18,27,36,17,26,35,44,37,38,39,40,41,42,43).forEach {
                setItem(it, frame)
            }

            setItem(20, ItemStack(Material.ENDER_PEARL).apply {
                val meta = itemMeta
                meta.displayName(Component.text("Random Teleport").color(NamedTextColor.BLUE))
                meta.lore(listOf(Component.text("Teleports to a random player!").color(NamedTextColor.GRAY)))
                itemMeta = meta
            })

            setItem(22, ItemStack(Material.DIAMOND_SWORD).apply {
                val meta = itemMeta
                meta.displayName(Component.text("Kill Yourself").color(NamedTextColor.RED))
                meta.lore(listOf(Component.text("Kills you.").color(NamedTextColor.GRAY)))
                itemMeta = meta
            })

            setItem(24, ItemStack(Material.BUCKET).apply {
                val meta = itemMeta
                meta.displayName(Component.text("Clear Inventory").color(NamedTextColor.BLUE))
                meta.lore(listOf(Component.text("Empties all your items.").color(NamedTextColor.GRAY)))
                itemMeta = meta
            })

            player.openInventory(this)
        }
    }

    override fun getInventory(): Inventory {
        return inventory
    }

}