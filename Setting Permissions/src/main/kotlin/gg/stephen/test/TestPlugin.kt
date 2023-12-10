package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        getCommand("perms")!!.setExecutor(PermsCommand(this))
        Bukkit.getPluginManager().registerEvents(PermsListener(), this)
    }

}