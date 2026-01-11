package gg.stephen.test

import io.papermc.paper.connection.PlayerGameConnection
import io.papermc.paper.dialog.Dialog
import io.papermc.paper.event.player.PlayerCustomClickEvent
import io.papermc.paper.registry.data.dialog.ActionButton
import io.papermc.paper.registry.data.dialog.DialogBase
import io.papermc.paper.registry.data.dialog.action.DialogAction
import io.papermc.paper.registry.data.dialog.body.DialogBody
import io.papermc.paper.registry.data.dialog.input.DialogInput
import io.papermc.paper.registry.data.dialog.input.SingleOptionDialogInput
import io.papermc.paper.registry.data.dialog.type.DialogType
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.net.URI

class TestPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        server.serverLinks.apply {
            addLink(Component.text("Discord"), URI("https://discord.gg/awddaw"))
            addLink(Component.text("X"), URI("https://x.com/awdawd"))
        }
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val dialog = Dialog.create { builder ->
            builder
                .empty()
                .base(
                    DialogBase.builder(Component.text("Choose your kit!"))
                        .body(listOf(
                            DialogBody.plainMessage(Component.text("Careful, your choice is important!"))
                        ))
                        .inputs(listOf(
                            DialogInput.bool("accept_rules", Component.text("I accept the server rules"))
                                .initial(false)
                                .build(),
                            DialogInput.text("nickname", Component.text("Your desired nickname"))
                                .initial("Player")
                                .maxLength(16)
                                .width(250)
                                .build(),
                            DialogInput.numberRange("power_level", Component.text("Power Level (1-100)"), 1F, 100F)
                                .step(1F)
                                .initial(50F)
                                .width(300)
                                .labelFormat("%s: %s")
                                .build(),
                            DialogInput.singleOption("starter_kit", Component.text("Choose starter kit"),
                                listOf(
                                    SingleOptionDialogInput.OptionEntry.create("warrior", Component.text("Warrior"), true),
                                    SingleOptionDialogInput.OptionEntry.create("mage", Component.text("mage"), false),
                                    SingleOptionDialogInput.OptionEntry.create("archer", Component.text("archer"), false),
                                )
                            )
                                .width(300)
                                .build()
                        ))
                        .canCloseWithEscape(false)
                        .build()
                    )
                .type(
                    DialogType.multiAction(listOf(
                        ActionButton.builder(Component.text("Miner", NamedTextColor.GREEN))
                            .tooltip(Component.text("Good with a pick"))
                            .action(DialogAction.customClick(Key.key("kit:miner"), null))
                            .build(),
                        ActionButton.builder(Component.text("Archer", NamedTextColor.RED))
                            .tooltip(Component.text("Good with a bow"))
                            .action(DialogAction.customClick(Key.key("kit:archer"), null))
                            .build(),
                        ActionButton.builder(Component.text("Bomber", NamedTextColor.GREEN))
                            .tooltip(Component.text("Good with TNT"))
                            .action(DialogAction.customClick(Key.key("kit:bomber"), null))
                            .build(),
                        ActionButton.builder(Component.text("Assassin", NamedTextColor.RED))
                            .tooltip(Component.text("Good with a sword"))
                            .action(DialogAction.customClick(Key.key("kit:assassin"), null))
                            .build()
                    )).build()
                )
        }

        val linksDialog = Dialog.create { builder ->
            builder
                .empty()
                .base(
                    DialogBase.builder(Component.text("Server Links"))
                        .body(listOf(
                            DialogBody.plainMessage(Component.text("Check out our community!"))
                        ))
                        .build(),
                )
                .type(
                    DialogType.serverLinks(
                        ActionButton.builder(
                            Component.text("Close"))
                            .build(),
                        2,
                        200
                    )
                )
        }

        e.player.showDialog(linksDialog)
    }

    @EventHandler
    fun onPlayerCustomClick(e: PlayerCustomClickEvent) {
        val key = e.identifier.asString()
        val view = e.dialogResponseView
        val player = (e.commonConnection as PlayerGameConnection).player

        println("accept rules: ${view?.getBoolean("accept_rules")}")
        println("power level: ${view?.getFloat("power_level") ?: 50f}")
        println("nickname: ${view?.getText("nickname") ?: "Player"}")
        println("kit: ${view?.getText("starter_kit") ?: "unknown"}")

        if (key.startsWith("rules:")) {
            val answer = key.substringAfter("rules:")
            player.sendPlainMessage(if (answer == "yes") "Great!" else "Bad!!!")
        }

        if (key.startsWith("kit:")) {
            val answer = key.substringAfter("kit:")
            when (answer) {
                "miner" -> {}
                "archer" -> {}
            }
            player.sendPlainMessage("You selected ${answer.replaceFirstChar { it.titlecase() }}!")
        }
    }

}