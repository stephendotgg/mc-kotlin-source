package gg.stephen.test.command

import gg.stephen.test.TestPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ReadConfigCommand(val testPlugin: TestPlugin) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        val config = testPlugin.config
        sender.sendPlainMessage(config.getString("word")!!)
        sender.sendPlainMessage(config.getInt("favorite-number").toString())
        sender.sendPlainMessage(config.getBoolean("likes-cake").toString())
        sender.sendPlainMessage(config.getStringList("favorite-colors").toString())

        return false
    }

}