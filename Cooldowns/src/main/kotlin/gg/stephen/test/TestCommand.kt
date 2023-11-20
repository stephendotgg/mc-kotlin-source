package gg.stephen.test

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit

class TestCommand : CommandExecutor {

    private val cooldown: Cache<Player, Long> = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        if (cooldown.asMap().containsKey(sender)) {
            // they are currently in the cooldown map, don't allow usage of command
            val millisTill = cooldown.asMap()[sender]!! - System.currentTimeMillis()
            sender.sendPlainMessage("You must wait ${TimeUnit.MILLISECONDS.toSeconds(millisTill)} seconds to use this!")
        } else {
            // they are not in the cooldown map, allow usage, and add to map
            sender.sendPlainMessage("Test worked!")
            cooldown.put(sender, System.currentTimeMillis() + 5000)
        }
        return false
    }

}