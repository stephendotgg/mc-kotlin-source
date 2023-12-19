package gg.stephen.test

import net.kyori.adventure.text.Component
import net.wesjd.anvilgui.AnvilGUI
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class BroadcastCommand(val testPlugin: TestPlugin) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        AnvilGUI.Builder()
            .onClose {
                sender.sendPlainMessage("You closed the anvil!")
            }
            .onClick { slot, stateSnapshot ->
                if (slot != AnvilGUI.Slot.OUTPUT) return@onClick emptyList()

                Bukkit.broadcast(Component.text(stateSnapshot.text))
                return@onClick listOf(AnvilGUI.ResponseAction.close())
            }
            .itemLeft(ItemStack(Material.DIAMOND_SWORD))
            .itemOutput(ItemStack(Material.PAPER))
            .title("What to broadcast?")
            .text("> ")
            .plugin(testPlugin)
            .open(sender)

        return false
    }

}