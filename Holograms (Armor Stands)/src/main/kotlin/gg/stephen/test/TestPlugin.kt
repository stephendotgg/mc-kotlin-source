package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    private val holograms = mutableListOf<ArmorStand>()

    override fun onEnable() {
        val world = Bukkit.getWorld("world")!!
        val location = Location(world, -279.5, 64.0, 2.5)

        arrayOf(
            Component.text("This is the best").color(NamedTextColor.GREEN),
            Component.text("Minecraft course").color(NamedTextColor.RED),
            Component.text("ever!").color(NamedTextColor.RED)
        ).forEach {
            holograms.add((world.spawnEntity(location.subtract(0.0, 0.3, 0.0), EntityType.ARMOR_STAND) as ArmorStand).apply {
                isVisible = false
                isInvulnerable = true
                setGravity(false)

                isCustomNameVisible = true
                customName(it)
            })
        }
    }

    override fun onDisable() {
        holograms.forEach {
            it.remove()
        }
    }

}