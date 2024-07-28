package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

enum class Rank(val prefix: Component, val order: Char) {

    OWNER(Component.text("Owner ", NamedTextColor.RED), 'a'),
    HELPER(Component.text("Helper ", NamedTextColor.GREEN), 'b'),
    GUEST(Component.text("Guest ", NamedTextColor.GRAY), 'c')

}