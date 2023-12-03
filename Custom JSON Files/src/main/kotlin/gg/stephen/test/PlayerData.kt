package gg.stephen.test

import java.sql.Timestamp
import java.util.*

data class PlayerData(
    val uuid: UUID,
    val coins: Int,
    val timestamp: Timestamp
)
