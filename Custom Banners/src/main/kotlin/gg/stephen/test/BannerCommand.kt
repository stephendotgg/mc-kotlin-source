package gg.stephen.test

import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.block.banner.Pattern
import org.bukkit.block.banner.PatternType
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta

class BannerCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        val banner = ItemStack(Material.WHITE_BANNER).apply {
            val meta = itemMeta as BannerMeta
            meta.patterns = listOf(
                Pattern(DyeColor.BLUE, PatternType.BORDER),
                Pattern(DyeColor.GREEN, PatternType.CREEPER),
                Pattern(DyeColor.GRAY, PatternType.TRIANGLE_TOP)
            )
            itemMeta = meta
        }

        sender.inventory.addItem(banner)

        return false
    }

}