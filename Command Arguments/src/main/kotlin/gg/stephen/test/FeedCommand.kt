package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class FeedCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size != 2) {
            sender.sendPlainMessage("Invalid usage! Use /feed <player> <amount>.")
            return false
        }

        val player = Bukkit.getPlayer(args[0])
        if (player == null) {
            sender.sendPlainMessage("The person you specified is not online!")
            return false
        }

        val amount = args[1].toIntOrNull()
        if (amount == null) {
            sender.sendPlainMessage("You specified an invalid number!")
            return false
        }

        player.foodLevel = (amount + player.foodLevel).coerceIn(0, 20)

        sender.sendPlainMessage("You gave food to ${player.name}.")
        player.sendPlainMessage("${sender.name} gave you food!")

        return false
    }

}