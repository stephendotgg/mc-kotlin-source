package gg.stephen.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class HealCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        if (!sender.hasPermission("testplugin.heal")) {
            sender.sendPlainMessage("You dont have permission!")
            return false
        }

        sender.health = 20.0
        sender.sendMessage(Component.text("Your health has been restored!")
            .color(NamedTextColor.GREEN)
            .decorate(TextDecoration.BOLD))

        return false
    }

}