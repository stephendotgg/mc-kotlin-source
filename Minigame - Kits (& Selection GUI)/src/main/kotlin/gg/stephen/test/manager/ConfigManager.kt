package gg.stephen.test.manager

import gg.stephen.test.Minigame
import org.bukkit.Bukkit
import org.bukkit.Location

object ConfigManager {

    val REQUIRED_PLAYERS = Minigame.INSTANCE.config.getInt("required-players")

    val COUNTDOWN_SECONDS = Minigame.INSTANCE.config.getInt("countdown-seconds")

    val LOBBY_SPAWN = Minigame.INSTANCE.config.run {
        Location(
            Bukkit.getWorld(getString("lobby-spawn.world")!!),
            getDouble("lobby-spawn.x"),
            getDouble("lobby-spawn.y"),
            getDouble("lobby-spawn.z"),
            getDouble("lobby-spawn.yaw").toFloat(),
            getDouble("lobby-spawn.pitch").toFloat(),
        )
    }

}