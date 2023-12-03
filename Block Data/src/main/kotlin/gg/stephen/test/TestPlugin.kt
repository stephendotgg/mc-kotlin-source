package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.block.data.Rail
import org.bukkit.block.data.type.Door
import org.bukkit.block.data.type.GlassPane
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
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
        if (e.action != Action.RIGHT_CLICK_BLOCK) return

        val state = e.clickedBlock!!.state
        val data = state.blockData
        when (data) {
            is GlassPane -> data.isWaterlogged = true
            is Rail -> data.shape = Rail.Shape.NORTH_WEST
            is Door -> data.isOpen = !data.isOpen
            else -> return
        }
        state.blockData = data
        state.update()
    }

}