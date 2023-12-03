package gg.stephen.test

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BroadcastCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        if (args.isEmpty()) {
            sender.sendPlainMessage("Invalid usage! Please provide a broadcast message.")
            return false
        }

        val joined = args.joinToString(" ")

        val event = ServerBroadcastEvent(sender, joined)
        Bukkit.getPluginManager().callEvent(event)

        if (event.isCancelled) return false

        Bukkit.broadcast(Component.text(joined))

        return false
    }

}