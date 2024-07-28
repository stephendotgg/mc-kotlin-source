package gg.stephen.test

import com.theokanning.openai.completion.CompletionRequest
import com.theokanning.openai.service.OpenAiService
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.entity.Player

object ConversationManager {

    val conversations = mutableMapOf<Player, String>()
    val service = OpenAiService("X")

    fun getResponse(player: Player, message: Component): Component  {
        conversations[player] += "\nHuman: ${(message as TextComponent).content()}\nAI:"
        return Component.text(service.createCompletion(CompletionRequest.builder()
            .prompt(conversations[player])
            .model("davinci-002")
            .temperature(1.0)
            .maxTokens(128)
            .topP(1.0)
            .frequencyPenalty(0.0)
            .presencePenalty(0.0)
            .stop(listOf("Human:", "AI:"))
            .build()).choices.get(0).text)
    }

}