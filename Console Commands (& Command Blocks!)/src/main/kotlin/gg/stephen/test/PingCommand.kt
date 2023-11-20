package gg.stephen.test

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.*
import org.bukkit.entity.Player

class PingCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        when (sender) {
            is Player -> {
                sender.sendPlainMessage("Pong!")
            }
            is BlockCommandSender -> {
                Bukkit.broadcast(Component.text("Pong!"))
            }
            is ConsoleCommandSender -> {
                println("Pong!")
            }
        }
        return false
    }

}