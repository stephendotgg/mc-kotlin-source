package gg.stephen.test.instance

import gg.stephen.test.manager.ConfigManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.scheduler.BukkitRunnable

class Countdown(private val arena: Arena) : BukkitRunnable() {

    private var secondsRemaining = ConfigManager.COUNTDOWN_SECONDS

    override fun run() {
        when (secondsRemaining) {
            15, 10, 3, 2, 1 -> {
                arena.sendMessage(Component.text("Game will start in $secondsRemaining!"))
            }
            0 -> {
                arena.players.forEach {
                    it.clearTitle()
                }

                arena.startGame()
            }
        }

        val title = Title.title(Component.text("$secondsRemaining seconds"), Component.text("until game starts"))
        arena.players.forEach {
            it.showTitle(title)
        }

        secondsRemaining--
    }

}