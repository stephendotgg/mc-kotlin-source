package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.time.Duration

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        e.player.sendActionBar(Component.text("Welcome to the server!").color(NamedTextColor.RED))

        e.player.showTitle(Title.title(
            Component.text("Welcome to the server!"),
            Component.text("Thanks for joining us!"),
            Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(5), Duration.ofSeconds(1))
        ))

        e.player.sendPlayerListHeaderAndFooter(
            Component.text("This is a header!"),
            Component.text("This is a footer!")
        )
    }

}