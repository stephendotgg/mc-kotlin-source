package gg.stephen.test.instance.game

import gg.stephen.test.instance.Arena
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack

class BlockPlaceGame(private val arena: Arena) : Game {

    private var points: MutableMap<Player, Int> = arena.players.associateWith { 0 }.toMutableMap()

    override fun onStart() {
        arena.sendMessage(Component.text("Game has started! Your objective is to place 20 blocks in the fastest time... Good luck!"))

        val blocks = ItemStack(Material.DIRT, 20)
        arena.players.forEach {
            it.inventory.addItem(blocks)
        }
    }

    fun addPoint(player: Player) {
        val newPoints = points[player]!! + 1

        if (newPoints == 20) {
            arena.sendMessage(Component.text("${player.name} HAS WON! Thank you for playing :)"))
            arena.reset(true)
            return
        }

        player.sendMessage(Component.text("+1 POINT!"))
        points[player] = newPoints
    }

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        if (!arena.players.contains(e.player)) return
        addPoint(e.player)
    }

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        points.remove(e.player)
    }

}