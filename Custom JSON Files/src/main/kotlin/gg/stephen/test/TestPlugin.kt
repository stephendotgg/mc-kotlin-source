package gg.stephen.test

import com.google.gson.Gson
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.sql.Timestamp
import java.time.Instant
import java.util.*

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        dataFolder.mkdir()

        val file = File(dataFolder, "data.json").apply {
            if (!exists()) {
                createNewFile()
            }
        }

        val gson = Gson()
        val data = PlayerData(UUID.fromString("69e8f7d5-11f9-4818-a3bb-7f237df32949"), 5, Timestamp.from(Instant.now()))

        FileWriter(file, false).use {
            gson.toJson(data, it)
        }

        FileReader(file).use {
            println(gson.fromJson(it, PlayerData::class.java))
        }
    }

}