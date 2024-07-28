package gg.stephen.test.manager

import gg.stephen.test.Minigame
import gg.stephen.test.instance.Arena
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

object ArenaManager {

    val arenas = mutableListOf<Arena>().apply {
        val config = Minigame.INSTANCE.config
        config.getConfigurationSection("arenas")!!.getKeys(false).forEachIndexed { index, key ->
            add(Arena(index,
                Location(
                    Bukkit.getWorld(config.getString("arenas.$key.player-spawn.world")!!),
                    config.getDouble("arenas.$key.player-spawn.x"),
                    config.getDouble("arenas.$key.player-spawn.y"),
                    config.getDouble("arenas.$key.player-spawn.z"),
                    config.getDouble("arenas.$key.player-spawn.yaw").toFloat(),
                    config.getDouble("arenas.$key.player-spawn.pitch").toFloat(),
                ),
                Location(
                    Bukkit.getWorld(config.getString("arenas.$key.sign.world")!!),
                    config.getDouble("arenas.$key.sign.x"),
                    config.getDouble("arenas.$key.sign.y"),
                    config.getDouble("arenas.$key.sign.z"),
                    config.getDouble("arenas.$key.sign.yaw").toFloat(),
                    config.getDouble("arenas.$key.sign.pitch").toFloat(),
                ))
            )
        }
    }

    fun find(player: Player): Arena? {
        return arenas.firstOrNull { it.players.contains(player) }
    }

    fun findBySign(location: Location): Arena? {
        return arenas.firstOrNull { it.sign == location }
    }

}