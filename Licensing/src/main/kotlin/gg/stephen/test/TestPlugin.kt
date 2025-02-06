package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.mclicense.library.MCLicense

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        if (!MCLicense.validateKey(this, "67a543632e58f6f3677597b6")) {
            return Bukkit.getPluginManager().disablePlugin(this)
        }

        // More code here
    }

}