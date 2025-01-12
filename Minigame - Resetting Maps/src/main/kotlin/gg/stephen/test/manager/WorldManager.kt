package gg.stephen.test.manager

import org.apache.commons.io.FileUtils
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import java.io.File

object WorldManager {

    fun resetArena(templateName: String): World? {
        val templateWorld = File(Bukkit.getWorldContainer(), templateName)
        val arenaName = templateName.removeSuffix("-template")
        val arenaWorld = File(Bukkit.getWorldContainer(), arenaName)

        Bukkit.getWorld(arenaName)?.let { world ->
            world.loadedChunks.forEach { it.unload(false) }
            Bukkit.unloadWorld(world, false)
        }

        if (arenaWorld.exists()) {
            FileUtils.deleteDirectory(arenaWorld)
        }

        FileUtils.copyDirectory(templateWorld, arenaWorld)
        return Bukkit.createWorld(WorldCreator(arenaName))
    }

}