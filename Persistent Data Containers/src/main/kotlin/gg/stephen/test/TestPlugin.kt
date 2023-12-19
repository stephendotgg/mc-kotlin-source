package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataHolder
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import java.time.LocalDate

class TestPlugin : JavaPlugin(), Listener {

    val KEY = NamespacedKey(this, "surname")

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        val entity = Bukkit.getPlayer("stxphen")?.persistentDataContainer
        val tileEntity = (Bukkit.getWorld("world")?.getBlockAt(0, 0, 0)?.state as? PersistentDataHolder)?.persistentDataContainer
        val itemMeta = ItemStack(Material.SPONGE).itemMeta.persistentDataContainer

        entity?.set(NamespacedKey(this, "date"), LocalDateDataType(), LocalDate.now())
        (entity?.get(NamespacedKey(this, "date"), LocalDateDataType()) as LocalDate).dayOfMonth
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val pdc = e.player.persistentDataContainer

//        pdc.set(KEY, PersistentDataType.STRING, "King")

        if (pdc.has(KEY)) {
            println(pdc.get(KEY, PersistentDataType.STRING))
        }
    }

}