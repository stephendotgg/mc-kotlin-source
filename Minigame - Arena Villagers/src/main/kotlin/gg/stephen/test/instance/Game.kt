package gg.stephen.test.instance

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerQuitEvent

class Game(private val arena: Arena) : Listener {

    private var points: MutableMap<Player, Int> = arena.players.associateWith { 0 }.toMutableMap()

    init {
        arena.sendMessage(Component.text("Game has started! Your objective is to break 20 blocks in the fastest time... Good luck!"))
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
    fun onBlockBreak(e: BlockBreakEvent) {
        if (!arena.players.contains(e.player)) return
        addPoint(e.player)
    }

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        points.remove(e.player)
    }

}