package gg.stephen.test

import gg.stephen.test.command.ArenaCommand
import gg.stephen.test.listener.ConnectionListener
import gg.stephen.test.manager.ArenaManager
import gg.stephen.test.manager.ConfigManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Minigame : JavaPlugin() {

    companion object {
        lateinit var INSTANCE: Minigame
    }

    override fun onEnable() {
        INSTANCE = this

        saveDefaultConfig()

        ConfigManager
        ArenaManager

        getCommand("arena")!!.setExecutor(ArenaCommand())

        Bukkit.getPluginManager().registerEvents(ConnectionListener(), this)
    }

}