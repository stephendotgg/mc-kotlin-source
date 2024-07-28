package gg.stephen.test.instance

import gg.stephen.test.GameState
import gg.stephen.test.Minigame
import gg.stephen.test.manager.ConfigManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.block.Sign
import org.bukkit.block.sign.Side
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList

class Arena(val id: Int, private val spawn: Location, val sign: Location) {

    var state = GameState.RECRUITING
    val players = mutableListOf<Player>()

    private var countdown: Countdown? = null
    private var game: Game? = null

    init {
        refreshSign()
    }

    fun startGame() {
        countdown!!.cancel()
        countdown = null

        game = Game(this)
        Bukkit.getPluginManager().registerEvents(game!!, Minigame.INSTANCE)

        updateState(GameState.LIVE)
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

        updateState(GameState.RECRUITING)
    }

    fun addPlayer(player: Player) {
        players.add(player)
        refreshSign()

        player.teleport(spawn)

        if (state == GameState.RECRUITING && players.size >= ConfigManager.REQUIRED_PLAYERS) {
            countdown = Countdown(this)
            countdown!!.runTaskTimer(Minigame.INSTANCE, 0, 20)

            updateState(GameState.COUNTDOWN)
        }
    }

    fun removePlayer(player: Player) {
        players.remove(player)
        refreshSign()

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

    private fun updateState(newState: GameState) {
        state = newState
        refreshSign()
    }

    private fun refreshSign() {
        val sign = sign.block.state as Sign
        sign.getSide(Side.FRONT).apply {
            line(0, Component.text("Arena $id"))
            line(1, Component.text(state.name))
            line(2, Component.text("Players: ${players.size}"))
        }
        sign.update()
    }

    fun sendMessage(component: Component) {
        players.forEach {
            it.sendMessage(component)
        }
    }

}