package gg.stephen.test.manager

import gg.stephen.test.Minigame
import net.kyori.adventure.text.minimessage.MiniMessage

object MessageManager {

    val LEAVE_ARENA = MiniMessage.miniMessage().deserialize(Minigame.messageConfig.getString("leave-arena") ?: "You have left the arena!")

}