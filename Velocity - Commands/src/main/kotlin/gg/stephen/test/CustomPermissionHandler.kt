package gg.stephen.test

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.permission.PermissionsSetupEvent
import com.velocitypowered.api.permission.PermissionFunction
import com.velocitypowered.api.permission.PermissionProvider
import com.velocitypowered.api.permission.PermissionSubject
import com.velocitypowered.api.permission.Tristate
import com.velocitypowered.api.proxy.Player

class CustomPermissionHandler : PermissionProvider {

    @Subscribe
    fun onPermissionSetup(event: PermissionsSetupEvent) {
        event.provider = this
    }

    override fun createFunction(subject: PermissionSubject): PermissionFunction {
        return object : PermissionFunction {
            override fun getPermissionValue(permission: String): Tristate {
                if (subject is Player) {
                    when (permission) {
                        "command.test" -> return Tristate.FALSE
                        "server.specific.permission" -> return if (subject.currentServer.get().serverInfo.name == "hub") Tristate.TRUE else Tristate.FALSE
                    }
                }

                return Tristate.UNDEFINED
            }
        }
    }

}