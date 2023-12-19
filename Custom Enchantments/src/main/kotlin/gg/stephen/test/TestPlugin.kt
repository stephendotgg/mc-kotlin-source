package gg.stephen.test

import org.bukkit.Bukkit
import org.bukkit.enchantments.Enchantment
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(ConnectionListener(), this)

        Enchantment::class.java.getDeclaredField("acceptingNew").apply {
            isAccessible = true
            set(null, true)
        }

        AutoSmeltingEnchantment().also {
            Enchantment.registerEnchantment(it)
            Bukkit.getPluginManager().registerEvents(it, this)
        }
    }

}