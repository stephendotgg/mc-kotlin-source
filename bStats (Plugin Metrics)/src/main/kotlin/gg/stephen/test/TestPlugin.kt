package gg.stephen.test

import org.bstats.bukkit.Metrics
import org.bstats.charts.SimplePie
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        val metrics = Metrics(this, 20689)
        metrics.addCustomChart(SimplePie("test_chart") {
            "Success"
        })
    }

}