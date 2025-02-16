package gg.stephen.test;

import com.google.inject.Inject
import com.velocitypowered.api.event.PostOrder
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.server.ServerInfo
import org.slf4j.Logger

@Plugin(
    id = "testplugin",
    name = "Test Plugin",
    version = BuildConstants.VERSION
)
class TestPlugin @Inject constructor(private val server: ProxyServer) {

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        server.eventManager.register(this, Listener(server))
    }

}
