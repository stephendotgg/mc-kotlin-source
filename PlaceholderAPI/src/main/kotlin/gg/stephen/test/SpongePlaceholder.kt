package gg.stephen.test

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.Material
import org.bukkit.entity.Player

class SpongePlaceholder : PlaceholderExpansion() {

    override fun getIdentifier(): String {
        return "sponge"
    }

    override fun getAuthor(): String {
        return "TestPlugin"
    }

    override fun getVersion(): String {
        return "1.0"
    }

    override fun onPlaceholderRequest(player: Player, params: String): String {
        return if (player.inventory.itemInMainHand.type == Material.SPONGE) "yes" else "no"
    }

}