/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.silyx.command.vannila.misc

import dev.yxqsnz.classes.api.MeowApi
import dev.yxqsnz.classes.command.CommandContext
import dev.yxqsnz.classes.command.TextCommand
import dev.yxqsnz.common.Colors
import dev.yxqsnz.common.Urls
import dev.yxqsnz.util.network.requests
import dev.yxqsnz.util.parseJson
import net.dv8tion.jda.api.EmbedBuilder

class CatCommand : TextCommand(Options) {
	companion object Options : TextCommand.Options("cat") {
		override var aliases: List<String> = listOf("gato")
		override var description: String = "Mostra uma foto de um gato"
	}
	
	override suspend fun execute(context: CommandContext) {
		with(context) {
			channel.sendTyping()
			val res = requests.get(Urls.MEOW_API)
			val resultJSON = parseJson<MeowApi>(res.body!!.string())
			val embed = EmbedBuilder()
			embed.setTitle("Meow!")
			embed.setDescription("[Clique aqui](${resultJSON!!.file}) caso a imagem não apareça.")
			embed.setColor(Colors.DISCORD_BLUE)
			embed.setImage(resultJSON.file)
			message.reply(embed.build()).queue()
			res.body!!.close()
		}
	}
}