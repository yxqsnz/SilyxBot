/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.silyx.command.vannila.info

import dev.yxqsnz.classes.command.CommandContext
import dev.yxqsnz.classes.command.TextCommand
import dev.yxqsnz.common.Colors
import dev.yxqsnz.common.Emotes
import net.dv8tion.jda.api.EmbedBuilder
import dev.yxqsnz.util.humanizeBytes as b

class BotInfoCommand : TextCommand(Options) {
	companion object Options : TextCommand.Options("BotInfo") {
		override var aliases: List<String> = listOf("stats", "status")
		override var description: String = "Minhas informações"
	}
	
	override suspend fun execute(context: CommandContext) {
		with(context) {
			channel.sendTyping()
			val runtime = Runtime.getRuntime()
			val usedMemory = (runtime.totalMemory() - runtime.freeMemory())
			val maxMemory = runtime.maxMemory()
			val usedPercentage = (100 * usedMemory) / maxMemory
			val freeMemory = maxMemory - usedMemory
			
			val botInfoEmbedBuilder = EmbedBuilder()
			botInfoEmbedBuilder.setTitle("Minhas informações")
			botInfoEmbedBuilder.setDescription(
				"""
				Olá, Me chamo Silyx um simples bot. fui feito pelo yxqsnz em ${Emotes.KOTLIN_LOGO} [Kotlin](https://kotlinlang.org/)
                usando a biblioteca ${Emotes.JDA_LOGO} [JDA](https://github.com/DV8FromTheWorld/JDA)
				
                :computer: Ram: `Usados ${b(usedMemory)}/${b(maxMemory)} ($usedPercentage% usados,${b(freeMemory)} Livres.) `
                ${Emotes.KOTLIN_LOGO} Versão do Kotlin: ${KotlinVersion.CURRENT}
                ${Emotes.JDA_LOGO} Versão do JDA: v4.2.1_261
			""".trimIndent()
			)
			botInfoEmbedBuilder.setColor(Colors.SILYX_RED)
			message.reply(botInfoEmbedBuilder.build()).queue()
		}
	}
}