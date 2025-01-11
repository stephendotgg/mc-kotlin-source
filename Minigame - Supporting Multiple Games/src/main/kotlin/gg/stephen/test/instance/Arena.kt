package gg.stephen.test.instance

import gg.stephen.test.GameState
import gg.stephen.test.Minigame
import gg.stephen.test.instance.game.Game
import gg.stephen.test.instance.game.GameType
import gg.stephen.test.manager.ConfigManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList

class Arena(val id: Int, private val spawn: Location, private val gameType: GameType) {

    var state = GameState.RECRUITING
    val players = mutableListOf<Player>()

    private var countdown: Countdown? = null
    private var game: Game? = null

    fun startGame() {
        countdown!!.cancel()
        countdown = null

        game = gameType.gameClass.getConstructor(Arena::class.java).newInstance(this).also {
            Bukkit.getPluginManager().registerEvents(it, Minigame.INSTANCE)
            it.onStart()
        }

        state = GameState.LIVE
    }

    fun reset(kickPlayers: Boolean) {
        if (kickPlayers) {
            players.forEach {
                it.teleport(ConfigManager.LOBBY_SPAWN)
            }
            players.clear()
        }

        countdown?.let {
            it.cancel()
            countdown = null
        }

        game?.let {
            HandlerList.unregisterAll(it)
            game = null
        }

        state = GameState.RECRUITING
    }

    fun addPlayer(player: Player) {
        players.add(player)
        player.teleport(spawn)

        if (state == GameState.RECRUITING && players.size >= ConfigManager.REQUIRED_PLAYERS) {
            countdown = Countdown(this)
            countdown!!.runTaskTimer(Minigame.INSTANCE, 0, 20)

            state = GameState.COUNTDOWN
        }
    }

    fun removePlayer(player: Player) {
        players.remove(player)
        player.teleport(ConfigManager.LOBBY_SPAWN)

        if (players.size >= ConfigManager.REQUIRED_PLAYERS) return

        if (state == GameState.COUNTDOWN) {
            sendMessage(Component.text("Not enough players. Countdown has stopped."))
            reset(false)
        } else if (state == GameState.LIVE) {
            sendMessage(Component.text("The game has ended as too many players have left."))
            reset(true)
        }
    }

    fun sendMessage(component: Component) {
        players.forEach {
            it.sendMessage(component)
        }
    }

}