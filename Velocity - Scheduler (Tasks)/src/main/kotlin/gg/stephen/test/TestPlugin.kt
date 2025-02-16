package gg.stephen.test;

import com.google.inject.Inject
import com.moandjiezana.toml.Toml
import com.moandjiezana.toml.TomlWriter
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.TimeUnit

@Plugin(
    id = "testplugin",
    name = "Test Plugin",
    version = BuildConstants.VERSION
)
class TestPlugin @Inject constructor(val logger: Logger, val server: ProxyServer) {

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        var countdown = 10
        val task = server.scheduler
            .buildTask(this, Runnable {
                if (countdown == 0) {
                    logger.info("Countdown ended!")
                    return@Runnable
                }

                countdown--
                logger.info("Countdown: $countdown")
            })
            .repeat(1, TimeUnit.SECONDS)
            .schedule()

        task.cancel()
    }

}
