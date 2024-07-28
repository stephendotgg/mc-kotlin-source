package gg.stephen.test.kit

import gg.stephen.test.kit.type.FighterKit
import gg.stephen.test.kit.type.MinerKit
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material

enum class KitType(val kitClass: Class<out Kit>, val display: Component, val description: Component, val material: Material) {

    FIGHTER(FighterKit::class.java, Component.text("Fighter", NamedTextColor.RED), Component.text("Best fighter kit!"), Material.IRON_SWORD),
    MINER(MinerKit::class.java, Component.text("Miner", NamedTextColor.GOLD), Component.text("Best miner kit!"), Material.IRON_PICKAXE)

}