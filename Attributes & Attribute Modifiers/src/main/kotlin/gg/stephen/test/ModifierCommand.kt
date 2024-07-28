package gg.stephen.test

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ModifierCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        val item = sender.inventory.itemInMainHand
        if (item.type == Material.AIR) {
            sender.sendPlainMessage("You must be holding something in your hand to run this.")
            return false
        }

        item.apply {
            val meta = itemMeta
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, AttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE.key.key, 100.0, AttributeModifier.Operation.ADD_NUMBER))
            itemMeta = meta
        }

        sender.sendPlainMessage("Your item has been given 100 attack damage!")

        return false
    }

}