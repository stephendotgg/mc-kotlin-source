package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class WarpsGUI(player: Player) : InventoryHolder {

    private val inventory = Bukkit.createInventory(this, 54, Component.text("Warps"))
    private val warps = MutableList(130) { ItemStack(Material.ENDER_PEARL) }
    var page = 1

    init {
        update()
        player.openInventory(inventory)
    }

    fun update() {
        inventory.apply {
            clear()

            val leftPageValid = PageUtil.isPageValid(page - 1, warps, 52)
            setItem(0, ItemStack(if (leftPageValid) Material.LIME_STAINED_GLASS_PANE else Material.RED_STAINED_GLASS_PANE).apply {
                val meta = itemMeta
                meta.displayName(Component.text("Page Left").color(if (leftPageValid) NamedTextColor.GREEN else NamedTextColor.RED))
                itemMeta = meta
            })

            val rightPageValid = PageUtil.isPageValid(page + 1, warps, 52)
            setItem(8, ItemStack(if (rightPageValid) Material.LIME_STAINED_GLASS_PANE else Material.RED_STAINED_GLASS_PANE).apply {
                val meta = itemMeta
                meta.displayName(Component.text("Page Right").color(if (rightPageValid) NamedTextColor.GREEN else NamedTextColor.RED))
                itemMeta = meta
            })

            PageUtil.getItems(page, warps, 52).forEach {
                setItem(firstEmpty(), it)
            }
        }
    }

    override fun getInventory(): Inventory {
        return inventory
    }

}