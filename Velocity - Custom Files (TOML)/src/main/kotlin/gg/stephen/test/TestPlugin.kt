package gg.stephen.test;

import com.google.inject.Inject
import com.moandjiezana.toml.Toml
import com.moandjiezana.toml.TomlWriter
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import org.slf4j.Logger
import java.nio.file.Files
import java.nio.file.Path

@Plugin(
    id = "testplugin",
    name = "Test Plugin",
    version = BuildConstants.VERSION
)
class TestPlugin @Inject constructor(val logger: Logger, @DataDirectory val dataDirectory: Path) {

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        Files.createDirectories(dataDirectory)

        val config = dataDirectory.resolve("config.toml")
        if (Files.notExists(config)) {
            // #1 Empty file
            // Files.createFile(config)

            // #2 Copy file from template
            javaClass.classLoader.getResourceAsStream("config.toml")?.use {
                Files.copy(it, config)
            }
        }

        Toml().read(config.toFile()).run {
            // Read
            logger.info(getString("easy.string"))
            logger.info(getLong("easy.number").toString())
            logger.info(getBoolean("easy.boolean").toString())
            logger.info(getList<Int>("easy.array").toString())

            logger.info(getTable("less-easy.settings").toMap().toString())

            val inv = getTables("less-easy.inventory").map {
                it.to(InventoryItem::class.java)
            }
            logger.info(inv.toString())

            // Set one field
            val data1 = this.toMap().toMutableMap()
            (data1["easy"] as MutableMap<String, Any>)["number"] = 100
            //TomlWriter().write(data1, config.toFile())

            // Set bulk
            val data2 = mapOf(
                "easy" to mapOf(
                    "string" to "banana",
                    "number" to 42,
                    "boolean" to true,
                    "array" to listOf(1,2,3,4)
                )
            )
            TomlWriter().write(data2, config.toFile())
        }
    }

}
