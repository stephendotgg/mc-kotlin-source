package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener
import java.util.*

class TestPlugin : JavaPlugin(), PluginMessageListener {

    override fun onEnable() {
        getCommand("serverswitcher")!!.setExecutor(ServerSwitcherCommand())
        Bukkit.getPluginManager().registerEvents(ServerSwitcherListener(this), this)

        Bukkit.getServer().messenger.registerIncomingPluginChannel(this, "custom:main", this)
        Bukkit.getServer().messenger.registerOutgoingPluginChannel(this, "custom:main")
    }

    override fun onPluginMessageReceived(channel: String, player: Player, data: ByteArray) {
        if (channel != "custom:main") return

        val split = data.decodeToString().split("|")
        val player = Bukkit.getPlayer(UUID.fromString(split[0])) ?: return

        player.inventory.addItem(ItemStack(Material.valueOf(split[1])))
    }

}