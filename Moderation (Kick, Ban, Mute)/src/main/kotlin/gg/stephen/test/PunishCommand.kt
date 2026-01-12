package gg.stephen.test

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import java.text.SimpleDateFormat
import java.util.Calendar

class PunishCommand : CommandExecutor, Listener {

    //     /punish <name> <kick/ban/tempban/mute/unmute>

    private val mutes = mutableListOf<Player>()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (args?.size != 2) {
            sender.sendPlainMessage("Wrong arguments! /punish <name> <kick/ban/tempban/mute/unmute>")
            return false
        }

        val target = Bukkit.getOfflinePlayer(args[0])
        when (args[1]) {
            "kick" -> {
                if (!target.isOnline) {
                    sender.sendPlainMessage("Target is not online!")
                    return false
                }
                target.player!!.kick(Component.text("You were kicked!"))
            }
            "ban" -> {
                target.banPlayer("You are banned!")
            }
            "tempban" -> {
                val cal = Calendar.getInstance()
                cal.add(Calendar.HOUR, 12)

                target.banPlayer("You are temp banned! Expires: ${SimpleDateFormat("MM/dd HH:mm").format(cal.time)}", cal.time)
            }
            "mute" -> {
                if (!target.isOnline) {
                    sender.sendPlainMessage("Target is not online!")
                    return false
                }

                if (target.player !in mutes) mutes.add(target.player!!)
            }
            "unmute" -> {
                if (!target.isOnline) {
                    sender.sendPlainMessage("Target is not online!")
                    return false
                }

                mutes.remove(target.player)
            }
            else -> {
                sender.sendPlainMessage("Wrong arguments! /punish <name> <kick/ban/tempban/mute/unmute")
            }
        }

        return false
    }

    @EventHandler
    fun onAsyncChatEvent(e: AsyncChatEvent) {
        if (mutes.contains(e.player)) {
            e.isCancelled = true
            e.player.sendPlainMessage("You are muted!")
        }
    }

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        mutes.remove(e.player)
    }

}