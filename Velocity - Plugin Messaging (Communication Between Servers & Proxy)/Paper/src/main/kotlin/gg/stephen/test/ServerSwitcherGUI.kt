package gg.stephen.test

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class ServerSwitcherGUI(player: Player) : InventoryHolder {

    private val inventory = Bukkit.createInventory(this, 45, Component.text("Server Switcher"))

    init {
        inventory.apply {
            listOf("lobby", "game").forEach {
                addItem(ItemStack(Material.DIAMOND).apply {
                    val meta = itemMeta
                    meta.displayName(Component.text(it))
                    itemMeta = meta
                })
            }

            player.openInventory(this)
        }
    }

    override fun getInventory(): Inventory {
        return inventory
    }

}