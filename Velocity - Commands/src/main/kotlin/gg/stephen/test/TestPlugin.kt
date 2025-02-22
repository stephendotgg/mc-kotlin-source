package gg.stephen.test

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger

@Plugin(
    id = "testplugin",
    name = "Test Plugin",
    version = BuildConstants.VERSION
)
class TestPlugin @Inject constructor(val proxyServer: ProxyServer) {

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        val commandMeta = proxyServer.commandManager.metaBuilder("test")
            .aliases("t1","t2")
            .plugin(this)
            .build()
        proxyServer.commandManager.register(commandMeta, TestCommand(proxyServer))

        proxyServer.eventManager.register(this, CustomPermissionHandler())
    }

}