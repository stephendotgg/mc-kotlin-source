package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class VanishCommand : CommandExecutor {

    companion object {
        val vanished = mutableListOf<Player>()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        if (vanished.contains(sender)) {
            vanished.remove(sender)
            Bukkit.getOnlinePlayers().forEach {
                it.showPlayer(sender)
            }
            sender.sendPlainMessage("You are now unvanished!")
        } else {
            vanished.add(sender)
            Bukkit.getOnlinePlayers().forEach {
                it.hidePlayer(sender)
            }
            sender.sendPlainMessage("You are now vanished!")
        }

        return false
    }

}