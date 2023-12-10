package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.net.URL
import java.util.*

class SkullCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        val playerSkull = ItemStack(Material.PLAYER_HEAD).apply {
            val meta = itemMeta as SkullMeta
            meta.setOwningPlayer(sender)
            itemMeta = meta
        }

        sender.inventory.addItem(playerSkull)

        val customSkull = ItemStack(Material.PLAYER_HEAD).apply {
            val meta = itemMeta as SkullMeta
            val profile = Bukkit.getServer().createProfile(UUID.randomUUID())
            val textures = profile.textures
            textures.skin = URL("http://textures.minecraft.net/texture/b54a09c8e36cc093e3753287a5cac4b37e5654ca518c6582ab99caa95134595e")
            profile.setTextures(textures)
            meta.playerProfile = profile
            itemMeta = meta
        }

        sender.inventory.addItem(customSkull)

        return false
    }

}