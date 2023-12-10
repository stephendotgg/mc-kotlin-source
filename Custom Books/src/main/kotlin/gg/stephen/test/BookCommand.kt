package gg.stephen.test

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

class BookCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        val book = ItemStack(Material.WRITTEN_BOOK).apply {
            val meta = itemMeta as BookMeta
            meta.author(text("Stephen"))
            meta.title(text("My awesome book!"))
            meta.pages(
                text()
                    .append(text("Clickable!").clickEvent(ClickEvent.clickEvent(ClickEvent.Action.CHANGE_PAGE, "2")))
                    .appendNewline()
                    .append(text("Woooooo!!").color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
                    .appendNewline()
                    .append(text("Hoverable!").hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, text("Best course ever!"))))
                    .build(),
                text("My second page!"))
            itemMeta = meta
        }

        sender.inventory.addItem(book)

        return false
    }

}