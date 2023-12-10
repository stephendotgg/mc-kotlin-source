package gg.stephen.test

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionAttachment

class PermsCommand(val testPlugin: TestPlugin) : CommandExecutor {

    companion object {
        val permissions = mutableMapOf<Player, PermissionAttachment>()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        if (args.size != 1) {
            sender.sendPlainMessage("Invalid usage! Please use /perms <permission>.")
            return false
        }

        val permission = args[0]
        val attachment = permissions.getOrPut(sender) { sender.addAttachment(testPlugin) }

        if (sender.hasPermission(permission)) {
            attachment.unsetPermission(permission)
            sender.sendPlainMessage("Unset the permission!")
        } else {
            attachment.setPermission(permission, true)
            sender.sendPlainMessage("Set the permission!")
        }

        return false
    }

}