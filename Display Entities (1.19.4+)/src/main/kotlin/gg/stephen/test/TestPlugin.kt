package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.*
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.Transformation
import org.joml.Quaternionf
import org.joml.Vector3f

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getWorld("world")?.let { world ->
            (world.spawnEntity(world.getBlockAt(-280, 66, 6).location, EntityType.TEXT_DISPLAY) as TextDisplay).apply {
                text(Component.text("This is some text!", NamedTextColor.BLUE))
                backgroundColor = Color.GRAY
                billboard = Display.Billboard.CENTER
            }

            (world.spawnEntity(world.getBlockAt(-281, 66, 2).location, EntityType.BLOCK_DISPLAY) as BlockDisplay).apply {
                block = Material.BIRCH_PLANKS.createBlockData()
                transformation = Transformation(
                    Vector3f(0f, 0f, 0f),
                    Quaternionf(),
                    Vector3f(2f, 2f, 2f),
                    Quaternionf()
                )
            }

            (world.spawnEntity(world.getBlockAt(-280, 66, -2).location, EntityType.ITEM_DISPLAY) as ItemDisplay).apply {
                itemStack = ItemStack(Material.DIAMOND_SWORD)
                itemDisplayTransform = ItemDisplay.ItemDisplayTransform.GUI
            }
        }
    }

}