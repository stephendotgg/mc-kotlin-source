package gg.stephen.test

import gg.stephen.test.command.ReadConfigCommand
import gg.stephen.test.command.SetConfigCommand
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()

        getCommand("readconfig")!!.setExecutor(ReadConfigCommand(this))
        getCommand("setconfig")!!.setExecutor(SetConfigCommand(this))
    }

}