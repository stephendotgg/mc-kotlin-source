package gg.stephen.test

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block

class Cuboid(x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) {

    val minX = minOf(x1, x2)
    val minY = minOf(y1, y2)
    val minZ = minOf(z1, z2)
    val maxX = maxOf(x1, x2)
    val maxY = maxOf(y1, y2)
    val maxZ = maxOf(z1, z2)

    fun getBlocks(world: World): List<Block> {
        val blocks = mutableListOf<Block>()
        for (x in minX..maxX) {
            for (y in minY..maxY) {
                for (z in minZ..maxZ) {
                    blocks.add(world.getBlockAt(x, y, z))
                }
            }
        }
        return blocks
    }

    fun contains(location: Location): Boolean {
        return location.x >= minX && location.x <= maxX &&
               location.y >= minY && location.y <= maxY &&
               location.z >= minZ && location.z <= maxZ
    }

}