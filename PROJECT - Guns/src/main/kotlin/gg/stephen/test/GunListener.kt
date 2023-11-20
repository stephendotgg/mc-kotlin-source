package gg.stephen.test

import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class GunListener : Listener {

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val player = e.player
        GunType.entries.forEach {
            player.inventory.addItem(ItemStack(it.material).apply {
                val meta = itemMeta
                meta.displayName(Component.text(it.name))
                itemMeta = meta
            })
        }
    }

    @EventHandler
    fun onPlayerInteract(e: PlayerInteractEvent) {
        if (e.hand != EquipmentSlot.HAND) return
        if (!e.action.isRightClick) return

        val gunType = GunType.entries.firstOrNull { it.material == e.material } ?: return
        e.player.launchProjectile(gunType.projectile)
    }

}