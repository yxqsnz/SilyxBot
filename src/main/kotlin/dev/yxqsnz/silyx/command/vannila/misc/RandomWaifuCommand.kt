/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.silyx.command.vannila.misc

import dev.yxqsnz.classes.api.WaifuPics
import dev.yxqsnz.classes.command.CommandContext
import dev.yxqsnz.classes.command.TextCommand
import dev.yxqsnz.common.Colors
import dev.yxqsnz.common.Urls
import dev.yxqsnz.util.network.requests
import dev.yxqsnz.util.parseJson
import net.dv8tion.jda.api.EmbedBuilder

class RandomWaifuCommand : TextCommand(Options) {
	override suspend fun execute(context: CommandContext) {
		var waifuType = "waifu"
		var sfw = "sfw"
		with(context) {
			val isNSFW = message.textChannel.isNSFW
			if (args.isNotEmpty()) {
				if (args[0] == "--nsfw") {
					waifuType = "waifu"
					sfw = "nsfw"
				}
				if (args.size > 1 && args[1] == "--nsfw")
					sfw = "nsfw"
				if (isNSFW) message.channel.sendTyping()
				else
					message.channel.sendTyping()
			}
			val waifuEmbedBuilder = EmbedBuilder()
			var title = waifuType.capitalize()
			
			if (isNSFW) waifuEmbedBuilder.setColor(Colors.SILYX_RED) else waifuEmbedBuilder.setColor(Colors.LORITTA_AQUA)
			if (isNSFW) title += " (NSFW)"
			val res = requests.get(Urls.WAIFU_PICS(sfw, waifuType))
			val result = parseJson<WaifuPics>(res.body!!.string())
			val url = result!!.url
			
			waifuEmbedBuilder.setDescription("**[Clique aqui]($url)** caso a imagem não apareça")
			waifuEmbedBuilder.setImage(url)
			waifuEmbedBuilder.setTitle(title)
			val m = message.reply(waifuEmbedBuilder.build())
			if (sfw == "nsfw") {
				if (isNSFW) m.queue()
				else {
					try {
						message.privateChannel.sendMessage(waifuEmbedBuilder.build())
					} catch (e: Exception) {
					}
				}
				
			} else m.queue()
			
		}
	}
	
	companion object Options : TextCommand.Options("RandomWaifuImage") {
		override var aliases: List<String> = listOf("waifu", "ImagemDeUmaWaifu", "ImagemWaifu")
		override var description: String = "Mostra uma imagem de uma waifu."
	}
}