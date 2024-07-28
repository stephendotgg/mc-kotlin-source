package gg.stephen.test

import gg.stephen.test.command.ArenaCommand
import gg.stephen.test.listener.ConnectionListener
import gg.stephen.test.manager.ArenaManager
import gg.stephen.test.manager.ConfigManager
import gg.stephen.test.manager.MessageManager
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Minigame : JavaPlugin() {

    companion object {
        lateinit var INSTANCE: Minigame
        lateinit var messageConfig: YamlConfiguration
    }

    override fun onEnable() {
        INSTANCE = this

        saveDefaultConfig()

        ConfigManager
        ArenaManager

        saveResource("messages.yml", false)
        messageConfig = YamlConfiguration.loadConfiguration(File(dataFolder, "messages.yml"))
        MessageManager

        getCommand("arena")!!.setExecutor(ArenaCommand())

        Bukkit.getPluginManager().registerEvents(ConnectionListener(), this)
    }

}