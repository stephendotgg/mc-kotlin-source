package gg.stephen.test

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin(), Listener {

    private lateinit var bossBar: BossBar

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        bossBar = BossBar.bossBar(
            Component.text("My Boss Bar!").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD),
            0.65F,
            BossBar.Color.BLUE,
            BossBar.Overlay.NOTCHED_10
        )
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        e.player.showBossBar(bossBar)
    }

}