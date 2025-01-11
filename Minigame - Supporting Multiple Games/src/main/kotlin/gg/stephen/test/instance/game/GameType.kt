package gg.stephen.test.instance.game

enum class GameType(val gameClass: Class<out Game>) {

    BLOCK_BREAK(BlockBreakGame::class.java),
    BLOCK_PLACE(BlockPlaceGame::class.java)

}