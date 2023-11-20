package gg.stephen.test

import org.bukkit.Material
import org.bukkit.entity.Egg
import org.bukkit.entity.EnderPearl
import org.bukkit.entity.Fireball
import org.bukkit.entity.Projectile

enum class GunType(
    val material: Material,
    val projectile: Class<out Projectile>
) {

    EGG_BLASTER(
        Material.IRON_HOE,
        Egg::class.java
    ),

    PEARL_BLASTER(
        Material.GOLDEN_HOE,
        EnderPearl::class.java
    ),

    FIRE_BLASTER(
        Material.DIAMOND_HOE,
        Fireball::class.java
    )

}