package gg.stephen.test.kit

import org.bukkit.event.Listener

interface Kit : Listener {

    fun onEnable()

    fun onDisable()

}