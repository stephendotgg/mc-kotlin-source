package gg.stephen.test.instance

import gg.stephen.test.Minigame
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitTask

class Game(private val arena: Arena) : Listener {

    val task: BukkitTask
    val alive = arena.players.toMutableList()

    init {
        arena.sendMessage(Component.text("Game has started! Your objective is to kill other players and be the last man standing. Use the loot in the chests. Good luck..."))
        arena.chests.forEach(Chest::populate)

        arrayOf(
            ItemStack(Material.DIRT, 24),
            ItemStack(Material.ARROW, 30),
            ItemStack(Material.WOODEN_SWORD)
        ).also { items ->
            alive.forEach {
                it.health = 20.0
                it.inventory.clear()
                it.inventory.addItem(*items)
            }
        }

        task = Bukkit.getScheduler().runTaskTimer(Minigame.INSTANCE, Runnable {
            alive.toList().forEach { player ->
                if (player.location.y <= arena.yDeath) {
                    handleKill(player, null, true)
                }
            }
        }, 10, 10)
    }

    fun handleKill(player: Player, killer: Player?, tpSpec: Boolean) {
        alive.remove(player)
        arena.sendMessage(Component.text("${player.name} was killed${if (killer != null) " by ${killer.name}" else ""}."))

        player.health = 20.0
        player.inventory.clear()

        if (alive.size == 1) {
            arena.sendMessage(Component.text("GAME OVER! ${alive[0].name} has won SkyWars!"))
            arena.reset(true)
        } else if (tpSpec) {
            player.teleport(arena.arenaSpawn)
        }
    }

    @EventHandler
    fun onEntityDamage(e: EntityDamageEvent) {
        val player = e.entity as? Player ?: return
        if (!alive.contains(player)) return

        if (player.health - e.finalDamage <= 0) {
            e.isCancelled = true

            val killer = if (e is EntityDamageByEntityEvent) { (e.damager as? Player)?.takeIf { alive.contains(it) } } else null
            handleKill(player, killer, true)
        }
    }

}