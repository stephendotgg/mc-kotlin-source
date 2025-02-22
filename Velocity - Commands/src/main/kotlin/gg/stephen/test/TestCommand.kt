package gg.stephen.test

import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import net.kyori.adventure.text.Component

class TestCommand(private val proxyServer: ProxyServer) : SimpleCommand {

    override fun execute(invocation: SimpleCommand.Invocation) {
        if (invocation.source() !is Player) return

        val args = invocation.arguments()
        invocation.source().sendMessage(Component.text("Hello world! ${args.joinToString(" ")}"))
    }

    override fun hasPermission(invocation: SimpleCommand.Invocation): Boolean {
        return invocation.source().hasPermission("command.test")
    }

    override fun suggest(invocation: SimpleCommand.Invocation): List<String> {
        return when(invocation.arguments().size) {
            0 -> proxyServer.allPlayers.map { it.username }
            1 -> listOf("apple", "pear", "grape")
            else -> emptyList()
        }
    }

}