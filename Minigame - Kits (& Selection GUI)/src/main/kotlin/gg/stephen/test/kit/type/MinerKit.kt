package gg.stephen.test.kit.type

import gg.stephen.test.kit.Kit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class MinerKit(val player: Player) : Kit {

    override fun onEnable() {
        player.inventory.addItem(ItemStack(Material.DIAMOND_PICKAXE))
        player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, Int.MAX_VALUE, 2))
    }

    override fun onDisable() {
        player.inventory.clear()
        player.removePotionEffect(PotionEffectType.NIGHT_VISION)
    }

}