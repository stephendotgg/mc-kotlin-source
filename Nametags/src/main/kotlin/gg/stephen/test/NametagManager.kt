package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.entity.Player

object NametagManager {

    private val TEMP_RANK = Rank.OWNER

    fun setNametags(player: Player) {
        player.scoreboard = Bukkit.getScoreboardManager().newScoreboard

        Rank.entries.forEach {
            player.scoreboard.registerNewTeam(it.order + it.name).apply {
                prefix(it.prefix)
            }
        }

        Bukkit.getOnlinePlayers().forEach {
            player.scoreboard.getTeam(TEMP_RANK.order + TEMP_RANK.name)!!.addEntry(it.name)
        }
    }

    fun newNametag(player: Player) {
        Bukkit.getOnlinePlayers().forEach {
            it.scoreboard.getTeam(TEMP_RANK.order + TEMP_RANK.name)!!.addEntry(player.name)
        }
    }

    fun removeNametag(player: Player) { // remove you from all scoreboards
        Bukkit.getOnlinePlayers().forEach {
            it.scoreboard.getEntryTeam(player.name)!!.removeEntry(player.name)
        }
    }

}