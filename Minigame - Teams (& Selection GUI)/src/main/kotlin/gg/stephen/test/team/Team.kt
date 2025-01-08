package gg.stephen.test.team

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material

enum class Team(val display: Component, val material: Material) {

    RED(Component.text("Red", NamedTextColor.RED), Material.RED_WOOL),
    BLUE(Component.text("Blue", NamedTextColor.BLUE), Material.BLUE_WOOL),
    GREEN(Component.text("Green", NamedTextColor.GREEN), Material.GREEN_WOOL)

}