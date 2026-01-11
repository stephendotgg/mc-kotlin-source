package gg.stephen.test

import com.destroystokyo.paper.profile.ProfileProperty
import io.papermc.paper.datacomponent.item.ResolvableProfile
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Mannequin
import org.bukkit.entity.Pose
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.MainHand
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getWorld("world")?.let { world ->
            (world.spawnEntity(world.getBlockAt(-280, 65, 3).location, EntityType.MANNEQUIN) as Mannequin).apply {
                profile = ResolvableProfile.resolvableProfile(Bukkit.createProfile("stxphen"))

                customName(Component.text("Stephen"))
                isCustomNameVisible = true
                description = Component.text("as a mannequin")

                mainHand = MainHand.LEFT
                equipment.setItemInMainHand(ItemStack(Material.WOODEN_SWORD))
                equipment.helmet = ItemStack(Material.DIAMOND_HELMET)

                pose = Pose.SLEEPING
            }

            (world.spawnEntity(world.getBlockAt(-280, 65, 5).location, EntityType.MANNEQUIN) as Mannequin).apply {
                profile = ResolvableProfile.resolvableProfile()
                    .uuid(UUID.randomUUID())
                    .name("dummy")
                    .addProperty(ProfileProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTc2ODA4NTI0NDY2NSwKICAicHJvZmlsZUlkIiA6ICIzNTk4NmQwMGEzMDY0NmFhYjhhYjI5MWMyOTc0ZjkyMSIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0dXR1XzEyMTIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWI5NmIwMjE2OGY3NWMxYWZiZWNkMGJkMWU4M2ZjNWE5YzNhNzVjMDIwYzVkMmQyMzUxZmVkNTg2NGIxY2ZiZSIKICAgIH0sCiAgICAiQ0FQRSIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY5YjdmMmExZDAwZDI2ZjMwZWZlM2Y5YWI5YWM4MTdiMWU2ZDM1ZjRmM2NmYjAzMjRlZjJkMzI4MjIzZDM1MCIKICAgIH0KICB9Cn0="))
                    .build()
            }
        }
    }

}