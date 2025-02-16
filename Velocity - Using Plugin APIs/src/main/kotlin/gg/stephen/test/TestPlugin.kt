package gg.stephen.test;

import com.alessiodp.lastloginapi.api.LastLogin
import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.ServerPreConnectEvent
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Dependency
import com.velocitypowered.api.plugin.Plugin
import org.slf4j.Logger

@Plugin(
    id = "testplugin",
    name = "Test Plugin",
    version = BuildConstants.VERSION,
    dependencies = [
        Dependency(id = "lastloginapi", optional = false)
    ]
)
class TestPlugin @Inject constructor(val logger: Logger) {

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {

    }

    @Subscribe
    fun onServerPreConnect(event: ServerPreConnectEvent) {
        logger.info(LastLogin.getApi().getPlayer(event.player.uniqueId).lastLogin.toString())
    }

}
