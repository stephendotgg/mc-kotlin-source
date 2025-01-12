package gg.stephen.test.instance

import gg.stephen.test.GameState
import gg.stephen.test.Minigame
import gg.stephen.test.manager.ConfigManager
import gg.stephen.test.manager.WorldManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList

class Arena(
    val id: Int,
    val arenaSpawn: Location,
    private val spawns: List<Location>,
    val chests: List<Chest>,
    val yDeath: Int
) {

    var state = GameState.RECRUITING
    val players = mutableListOf<Player>()

    private var countdown: Countdown? = null
    private var game: Game? = null

    fun startGame() {
        countdown!!.cancel()
        countdown = null

        players.forEachIndexed { index, player ->
            player.teleport(spawns[index])
        }

        game = Game(this)
        Bukkit.getPluginManager().registerEvents(game!!, Minigame.INSTANCE)

        state = GameState.LIVE
    }

    fun reset(kickPlayers: Boolean) {
        state = GameState.LOADING

        countdown?.let {
            it.cancel()
            countdown = null
        }

        game?.let {
            it.task.cancel()
            HandlerList.unregisterAll(it)
            game = null
        }

        if (kickPlayers) {
            players.forEach {
                it.inventory.clear()
                it.teleport(ConfigManager.LOBBY_SPAWN)
            }
            players.clear()

            val newWorld = WorldManager.resetArena(arenaSpawn.world!!.name + "-template")
            newWorld?.let { world ->
                arenaSpawn.world = world
                spawns.forEach { spawn ->
                    spawn.world = world
                }
                chests.forEach { chest ->
                    chest.location.world = world
                }
            }
        }

        state = GameState.RECRUITING
    }

    fun addPlayer(player: Player) {
        players.add(player)
        player.teleport(arenaSpawn)

        if (state == GameState.RECRUITING && players.size >= ConfigManager.REQUIRED_PLAYERS) {
            countdown = Countdown(this)
            countdown!!.runTaskTimer(Minigame.INSTANCE, 0, 20)

            state = GameState.COUNTDOWN
        }
    }

    fun removePlayer(player: Player) {
        players.remove(player)
        if (game?.alive?.contains(player) == true) {
            game?.handleKill(player, null, false)
        }

        player.teleport(ConfigManager.LOBBY_SPAWN)
        player.inventory.clear()

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