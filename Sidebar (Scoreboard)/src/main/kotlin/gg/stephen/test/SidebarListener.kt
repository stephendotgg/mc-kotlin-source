package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot

class SidebarListener : Listener {

    val blocksBroken = mutableMapOf<Player, Int>()

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val board = Bukkit.getScoreboardManager().newScoreboard

        val objective = board.registerNewObjective("testboard", Criteria.DUMMY, Component.text("Test Board!", NamedTextColor.GREEN, TextDecoration.BOLD))
        objective.displaySlot = DisplaySlot.SIDEBAR

        board.registerNewTeam("website").apply {
            addEntry(ChatColor.AQUA.toString())
            prefix(Component.text("www.testwebsite.com", NamedTextColor.YELLOW))

            objective.getScore(ChatColor.AQUA.toString()).score = 1
        }

        board.registerNewTeam("blank").apply {
            addEntry(ChatColor.BLUE.toString())
            prefix(Component.text(" "))

            objective.getScore(ChatColor.BLUE.toString()).score = 2
        }

        board.registerNewTeam("blocksBroken").apply {
            addEntry(ChatColor.DARK_AQUA.toString())
            prefix(Component.text("Blocks broken: ", NamedTextColor.BLUE))
            suffix(Component.text("0", NamedTextColor.YELLOW))

            objective.getScore(ChatColor.DARK_AQUA.toString()).score = 3
        }


        e.player.scoreboard = board
    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val player = e.player

        blocksBroken[player] = blocksBroken[player]?.plus(1) ?: 1
        player.scoreboard.getTeam("blocksBroken")?.suffix(Component.text(blocksBroken[player].toString(), NamedTextColor.YELLOW))
    }

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        blocksBroken.remove(e.player)
    }

}