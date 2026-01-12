package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        val punish = PunishCommand()
        getCommand("punish")!!.setExecutor(punish)
        Bukkit.getPluginManager().registerEvents(punish, this)
    }

}