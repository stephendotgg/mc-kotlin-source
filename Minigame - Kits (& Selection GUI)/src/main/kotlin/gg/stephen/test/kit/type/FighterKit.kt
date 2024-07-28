package gg.stephen.test.kit.type

import gg.stephen.test.kit.Kit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class FighterKit(val player: Player) : Kit {

    override fun onEnable() {
        player.inventory.addItem(ItemStack(Material.DIAMOND_SWORD))
    }

    override fun onDisable() {
        player.inventory.clear()
    }

}