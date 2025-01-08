package gg.stephen.test.command

import gg.stephen.test.GameState
import gg.stephen.test.manager.ArenaManager
import gg.stephen.test.team.TeamGUI
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ArenaCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        when {
            args.size == 1 && args[0].equals("list", true) -> {
                sender.sendMessage(Component.text("Available arenas:"))
                ArenaManager.arenas.forEach {
                    sender.sendMessage(Component.text("- ${it.id} (${it.state})"))
                }
            }
            args.size == 1 && args[0].equals("team", true) -> {
                val arena = ArenaManager.find(sender)
                if (arena == null) {
                    sender.sendMessage(Component.text("You're not in an arena!"))
                    return false
                }

                if (arena.state == GameState.LIVE) {
                    sender.sendMessage(Component.text("You cannot choose a team at this time."))
                    return false
                }

                TeamGUI(sender, arena)
            }
            args.size == 1 && args[0].equals("quit", true) -> {
                val arena = ArenaManager.find(sender)
                if (arena == null) {
                    sender.sendMessage(Component.text("You're not in an arena!"))
                    return false
                }

                sender.sendMessage(Component.text("You have left the arena!"))
                arena.removePlayer(sender)
            }
            args.size == 2 && args[0].equals("join", true) -> {
                if (ArenaManager.find(sender) != null) {
                    sender.sendMessage(Component.text("You're already in an arena!"))
                    return false
                }

                val id = args[1].toIntOrNull()
                if (id == null || id < 0 || id >= ArenaManager.arenas.size) {
                    sender.sendMessage(Component.text("You specified an invalid arena ID."))
                    return false
                }

                val arena = ArenaManager.arenas.first { it.id == id }
                if (arena.state == GameState.LIVE) {
                    sender.sendMessage(Component.text("You can't join while the game is live."))
                    return false
                }

                sender.sendMessage(Component.text("You have joined the arena!"))
                arena.addPlayer(sender)
            }
            else -> {
                sender.sendMessage(Component.text("Invalid usage! Please use /arena list/quit/join [ID]."))
            }
        }

        return false
    }

}