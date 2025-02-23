package gg.stephen.test

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        //sender.sendPlainMessage(PlaceholderAPI.setPlaceholders(sender, "Your group is: %luckperms_highest_group_by_weight%"))
        sender.sendPlainMessage(PlaceholderAPI.setPlaceholders(sender, "Holding sponge: %sponge%"))
        return false
    }

}