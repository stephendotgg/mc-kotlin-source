package gg.stephen.test.command

import gg.stephen.test.TestPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class SetConfigCommand(val testPlugin: TestPlugin) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        val config = testPlugin.config
        config.set("likes-cake", false)
        config.set("favorite-instructor", "stephen")
        testPlugin.saveConfig()

        return false
    }

}