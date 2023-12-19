package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        getCommand("warps")!!.setExecutor(WarpsCommand())
        Bukkit.getPluginManager().registerEvents(GUIListener(), this)
    }

}