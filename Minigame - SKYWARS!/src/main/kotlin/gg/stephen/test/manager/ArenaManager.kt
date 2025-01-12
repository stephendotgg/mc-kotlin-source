package gg.stephen.test.manager

import gg.stephen.test.Minigame
import gg.stephen.test.instance.Arena
import gg.stephen.test.instance.Chest
import org.bukkit.Location
import org.bukkit.entity.Player

object ArenaManager {

    val arenas = mutableListOf<Arena>().apply {
        val config = Minigame.INSTANCE.config
        config.getConfigurationSection("arenas")!!.getKeys(false).forEachIndexed { index, key ->
            val world = WorldManager.resetArena(config.getString("arenas.$key.world")!!)!!
            add(
                Arena(
                    index,
                    Location(
                        world,
                        config.getDouble("arenas.$key.arena-spawn.x"),
                        config.getDouble("arenas.$key.arena-spawn.y"),
                        config.getDouble("arenas.$key.arena-spawn.z"),
                        config.getDouble("arenas.$key.arena-spawn.yaw").toFloat(),
                        config.getDouble("arenas.$key.arena-spawn.pitch").toFloat(),
                    ),
                    config.getConfigurationSection("arenas.$key.spawns")!!.getKeys(false).map { spawnKey ->
                        Location(
                            world,
                            config.getDouble("arenas.$key.spawns.$spawnKey.x"),
                            config.getDouble("arenas.$key.spawns.$spawnKey.y"),
                            config.getDouble("arenas.$key.spawns.$spawnKey.z"),
                            config.getDouble("arenas.$key.spawns.$spawnKey.yaw").toFloat(),
                            config.getDouble("arenas.$key.spawns.$spawnKey.pitch").toFloat()
                        )
                    },
                    config.getStringList("arenas.$key.chests").map { chestString ->
                        val parts = chestString.split("|")
                        Chest(
                            Chest.ChestTier.valueOf(parts[0]),
                            Location(world, parts[1].toDouble(), parts[2].toDouble(), parts[3].toDouble())
                        )
                    },
                    config.getInt("arenas.$key.y-death")
                )
            )
        }
    }

    fun find(player: Player): Arena? {
        return arenas.firstOrNull { it.players.contains(player) }
    }

}