package gg.stephen.test

import org.bukkit.entity.Player
import org.bukkit.map.MapCanvas
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView
import org.bukkit.map.MinecraftFont
import java.awt.Color
import java.net.URL
import javax.imageio.ImageIO

class CustomRenderer : MapRenderer() {

    val image = ImageIO.read(URL("https://i.imgflip.com/2/1gibro.jpg"))

    override fun render(map: MapView, canvas: MapCanvas, player: Player) {
        // PIXELS
        canvas.setPixelColor(10, 10, Color.RED)

        for (x in 25..50) {
            for (y in 25..50) {
                canvas.setPixelColor(x, y, Color.GREEN)
            }
        }

        // IMAGE
        canvas.drawImage(0, 0, image)

        // TEXT
        canvas.drawText(20, 40, MinecraftFont.Font, "Hello world!")
    }

}