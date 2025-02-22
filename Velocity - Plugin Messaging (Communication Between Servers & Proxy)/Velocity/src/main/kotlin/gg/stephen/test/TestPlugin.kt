package gg.stephen.test;

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PluginMessageEvent
import com.velocitypowered.api.event.player.ServerConnectedEvent
import com.velocitypowered.api.event.player.ServerPostConnectEvent
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.ServerConnection
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier
import com.velocitypowered.api.proxy.messages.PluginMessageEncoder
import org.slf4j.Logger

@Plugin(
    id = "testplugin",
    name = "Test Plugin",
    version = BuildConstants.VERSION
)
class TestPlugin @Inject constructor(val proxyServer: ProxyServer, val logger: Logger) {

    private val IDENTIFIER = MinecraftChannelIdentifier.from("custom:main")

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        proxyServer.channelRegistrar.register(IDENTIFIER)
    }

    @Subscribe
    fun onServerPostConnect(event: ServerPostConnectEvent) {
        val message = event.player.currentServer.get().sendPluginMessage(IDENTIFIER, "${event.player.uniqueId}|DIAMOND".toByteArray())
        logger.info(message.toString())
    }

    @Subscribe
    fun onPluginMessage(event: PluginMessageEvent) {
        if (!IDENTIFIER.equals(event.identifier)) return
        if (event.source !is ServerConnection) return

        val split = event.data.decodeToString().split("|")
        if (split[0] != "switch") return

        (event.source as ServerConnection).player.createConnectionRequest(proxyServer.getServer(split[1]).get()).fireAndForget()
    }

}
