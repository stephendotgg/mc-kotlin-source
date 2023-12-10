package gg.stephen.test

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        println(PlaceholderAPI.isRegistered("%banana%"))
    }

}