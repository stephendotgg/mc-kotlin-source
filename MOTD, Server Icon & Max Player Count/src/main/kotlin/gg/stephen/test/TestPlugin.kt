package gg.stephen.test

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerListPingEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.CachedServerIcon
import java.io.File

class TestPlugin : JavaPlugin(), Listener {

    private lateinit var icon: CachedServerIcon

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        icon = Bukkit.loadServerIcon(File("icon.png"))
    }

    @EventHandler
    fun onServerListPing(e: ServerListPingEvent) {
        e.motd(text()
            .append(text("Welcome to our server!").color(NamedTextColor.GREEN))
            .appendNewline()
            .append(text("We hope you have a good time!").color(NamedTextColor.GRAY))
            .build())

        e.maxPlayers = 100

        e.setServerIcon(icon)
    }

}