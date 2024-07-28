package gg.stephen.test.instance

import gg.stephen.test.GameState
import gg.stephen.test.Minigame
import gg.stephen.test.kit.Kit
import gg.stephen.test.kit.KitType
import gg.stephen.test.manager.ConfigManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList

class Arena(val id: Int, private val spawn: Location) {

    var state = GameState.RECRUITING
    val players = mutableListOf<Player>()
    val kits = mutableMapOf<Player, Kit>()

    private var countdown: Countdown? = null
    private var game: Game? = null

    fun startGame() {
        countdown!!.cancel()
        countdown = null

        game = Game(this)
        Bukkit.getPluginManager().registerEvents(game!!, Minigame.INSTANCE)

        state = GameState.LIVE
    }

    fun reset(kickPlayers: Boolean) {
        if (kickPlayers) {
            players.forEach {
                it.teleport(ConfigManager.LOBBY_SPAWN)
            }
            players.clear()

            kits.values.forEach {
                it.onDisable()
            }
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
        kits.remove(player)?.apply {
            onDisable()
            HandlerList.unregisterAll(this)
        }
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

    fun setKit(player: Player, type: KitType) {
        kits[player] = type.kitClass.getConstructor(Player::class.java).newInstance(player).apply {
            Bukkit.getPluginManager().registerEvents(this, Minigame.INSTANCE)
        }
    }

    fun sendMessage(component: Component) {
        players.forEach {
            it.sendMessage(component)
        }
    }

}