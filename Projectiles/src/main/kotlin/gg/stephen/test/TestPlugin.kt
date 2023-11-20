package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Egg
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerInteract(e: PlayerInteractEvent) {
        if (e.hand != EquipmentSlot.HAND) return
        if (!e.action.isRightClick) return
        if (e.player.inventory.itemInMainHand.type != Material.DIAMOND_HOE) return

        e.player.launchProjectile(Egg::class.java)
    }

}