package gg.stephen.test

import com.velocitypowered.api.event.ResultedEvent
import com.velocitypowered.api.event.ResultedEvent.GenericResult
import com.velocitypowered.api.proxy.Player

data class CustomEvent(
    val sender: Player,
    val recipient: Player,
    val message: String
) : ResultedEvent<GenericResult> {

    private var result: GenericResult = GenericResult.allowed()

    override fun getResult(): GenericResult = result

    override fun setResult(result: GenericResult) {
        this.result = result
    }

}