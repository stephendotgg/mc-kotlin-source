package gg.stephen.test

import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.File

class PlaySongCommand(val dataFolder: File) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        RadioSongPlayer(NBSDecoder.parse(File(dataFolder, "All Star.nbs"))).apply {
            addPlayer(sender)
            isPlaying = true
        }

        return false
    }

}