package gg.stephen.test.manager

import gg.stephen.test.Minigame
import gg.stephen.test.instance.Arena
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.entity.Villager

object ArenaManager {

    val arenas = mutableListOf<Arena>().apply {
        val config = Minigame.INSTANCE.config
        config.getConfigurationSection("arenas")!!.getKeys(false).forEachIndexed { index, key ->
            add(Arena(
                index,
                Location(
                    Bukkit.getWorld(config.getString("arenas.$key.player-spawn.world")!!),
                    config.getDouble("arenas.$key.player-spawn.x"),
                    config.getDouble("arenas.$key.player-spawn.y"),
                    config.getDouble("arenas.$key.player-spawn.z"),
                    config.getDouble("arenas.$key.player-spawn.yaw").toFloat(),
                    config.getDouble("arenas.$key.player-spawn.pitch").toFloat(),
                ),
                Location(
                    Bukkit.getWorld(config.getString("arenas.$key.villager-spawn.world")!!),
                    config.getDouble("arenas.$key.villager-spawn.x"),
                    config.getDouble("arenas.$key.villager-spawn.y"),
                    config.getDouble("arenas.$key.villager-spawn.z"),
                    config.getDouble("arenas.$key.villager-spawn.yaw").toFloat(),
                    config.getDouble("arenas.$key.villager-spawn.pitch").toFloat(),
                ))
            )
        }
    }

    fun find(player: Player): Arena? {
        return arenas.firstOrNull { it.players.contains(player) }
    }

    fun findByVillager(villager: Villager): Arena? {
        return arenas.firstOrNull { it.villager == villager }
    }

}