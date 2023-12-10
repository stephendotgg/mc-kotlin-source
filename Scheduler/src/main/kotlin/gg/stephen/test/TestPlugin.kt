package gg.stephen.test

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        // RUN TASK
        Bukkit.getScheduler().runTask(this, Runnable { })

        Bukkit.getScheduler().runTaskAsynchronously(this, Runnable { })

        // RUN TASK LATER
        Bukkit.getScheduler().runTaskLater(this, Runnable {
            Bukkit.broadcast(Component.text("Server has been up for 10 seconds!"))
        }, 200)

        // RUN TASK TIMER
        Bukkit.getScheduler().runTaskTimer(this, Runnable {
            Bukkit.broadcast(Component.text("It's been 15 seconds  - and this goes every 1 second!"))
        }, 300, 20)

        // COUNTDOWN (TASK TIMER)
        object : BukkitRunnable() {
            var countdown = 10
            override fun run() {
                if (countdown == 0) {
                    Bukkit.broadcast(Component.text("Countdown has ended. Start game or something..."))
                    cancel()
                    return
                }

                Bukkit.broadcast(Component.text("Countdown value: $countdown"))
                countdown--
            }
        }.runTaskTimer(this, 400, 20)
    }

}