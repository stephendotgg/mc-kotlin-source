package gg.stephen.test

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PostLoginEvent
import com.velocitypowered.api.event.proxy.ProxyPingEvent
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.server.ServerPing
import net.kyori.adventure.text.Component
import java.util.*

class Listener(private val server: ProxyServer) {

    @Subscribe
    fun onPostLogin(event: PostLoginEvent) {
        event.player.createConnectionRequest(server.getServer("game").get()).fireAndForget()
    }

    @Subscribe
    fun onProxyPing(event: ProxyPingEvent) {
        event.ping = ServerPing.builder()
            .version(event.ping.version)
            .onlinePlayers(50)
            .maximumPlayers(100)
            .description(Component.text("My Velocity network!\nCome join us!"))
            .samplePlayers(ServerPing.SamplePlayer("Stephen", UUID.randomUUID()))
            .build()
    }

}