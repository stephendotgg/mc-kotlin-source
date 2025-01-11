package gg.stephen.test.manager

import gg.stephen.test.Minigame
import gg.stephen.test.instance.Arena
import gg.stephen.test.instance.game.GameType
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

object ArenaManager {

    val arenas = mutableListOf<Arena>().apply {
        val config = Minigame.INSTANCE.config
        config.getConfigurationSection("arenas")!!.getKeys(false).forEachIndexed { index, key ->
            add(Arena(
                index,
                Location(
                    Bukkit.getWorld(config.getString("arenas.$key.world")!!),
                    config.getDouble("arenas.$key.x"),
                    config.getDouble("arenas.$key.y"),
                    config.getDouble("arenas.$key.z"),
                    config.getDouble("arenas.$key.yaw").toFloat(),
                    config.getDouble("arenas.$key.pitch").toFloat()
                ),
                GameType.valueOf(config.getString("arenas.$key.game")!!)
            ))
        }
    }

    fun find(player: Player): Arena? {
        return arenas.firstOrNull { it.players.contains(player) }
    }

}