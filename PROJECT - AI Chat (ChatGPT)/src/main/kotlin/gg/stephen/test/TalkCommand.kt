package gg.stephen.test

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TalkCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        if (ConversationManager.conversations.containsKey(sender)) {
            ConversationManager.conversations.remove(sender)
            sender.sendPlainMessage("Your conversation with the AI bot has ended.")
        } else {
            ConversationManager.conversations[sender] = "You are a pirate. You must speak like a pirate. You must never break character." +
                    "\nHuman: Hello!" +
                    "\nAI:"
            sender.sendPlainMessage("Your conversation with the AI bot has started! Introduce yourself...")
        }

        return false
    }

}