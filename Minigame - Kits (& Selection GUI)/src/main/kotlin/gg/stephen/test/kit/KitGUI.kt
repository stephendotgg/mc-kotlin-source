package gg.stephen.test.kit

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class KitGUI(player: Player) : InventoryHolder {

    private val inventory = Bukkit.createInventory(this, 54, Component.text("Kit Selection", NamedTextColor.BLUE))

    init {
        inventory.apply {
            KitType.entries.forEach {
                addItem(ItemStack(it.material).apply {
                    val meta = itemMeta
                    meta.displayName(it.display)
                    meta.lore(listOf(it.description))
                    meta.setLocalizedName(it.name)
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