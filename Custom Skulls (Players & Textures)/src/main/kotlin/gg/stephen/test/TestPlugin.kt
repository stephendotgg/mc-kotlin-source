package gg.stephen.test

import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        getCommand("skull")!!.setExecutor(SkullCommand())
    }

}