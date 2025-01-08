package gg.stephen.test.team

import gg.stephen.test.instance.Arena
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class TeamGUI(player: Player, arena: Arena) : InventoryHolder {

    private val inventory = Bukkit.createInventory(this, 54, Component.text("Team Selection", NamedTextColor.BLUE))

    init {
        inventory.apply {
            Team.entries.forEach {
                addItem(ItemStack(it.material).apply {
                    val meta = itemMeta
                    meta.displayName(it.display)
                    meta.lore(listOf(Component.text("${arena.teams.values.count { team -> team == it }} players", NamedTextColor.GRAY)))
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