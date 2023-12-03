package gg.stephen.test

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        dataFolder.mkdir()

        createBlankFile()
        createTemplateFile()
    }

    private fun createBlankFile() {
        val file = File(dataFolder, "data.yml").apply {
            if (!exists()) {
                createNewFile()
            }
        }

        val config = YamlConfiguration.loadConfiguration(file)
        config.set("car", "tesla")
        config.save(file)

        println(config.get("car"))
    }

    private fun createTemplateFile() {
        saveResource("settings.yml", false)
        val config = YamlConfiguration.loadConfiguration(File(dataFolder, "settings.yml"))
    }

}