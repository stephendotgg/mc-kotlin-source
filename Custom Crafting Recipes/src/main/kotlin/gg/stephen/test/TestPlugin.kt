package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        ShapedRecipe(NamespacedKey(this, "cool_sword"), ItemStack(Material.DIAMOND_SWORD)).apply {
            shape(
                " D ",
                " D ",
                " D "
            )
            setIngredient('D', Material.DIAMOND)

            Bukkit.addRecipe(this)
        }

        ShapedRecipe(NamespacedKey(this, "cool_barrier"), ItemStack(Material.BARRIER)).apply {
            shape(
                "R R",
                " R ",
                "R R"
            )
            setIngredient('R', Material.REDSTONE_BLOCK)

            Bukkit.addRecipe(this)
        }

        ShapedRecipe(NamespacedKey(this, "cool_stick"), ItemStack(Material.STICK).apply {
            val meta = itemMeta
            meta.displayName(Component.text("My cool stick!").color(NamedTextColor.RED))
            meta.lore(listOf(Component.text("Super stick!")))
            itemMeta = meta
        }).apply {
            shape(
                "GGG",
                "GSG",
                "GGG"
            )
            setIngredient('G', Material.GOLD_INGOT)
            setIngredient('S', ItemStack(Material.STICK).apply {
                val meta = itemMeta
                meta.displayName(Component.text("Super Stick"))
                itemMeta = meta
            })

            Bukkit.addRecipe(this)
        }
    }

}