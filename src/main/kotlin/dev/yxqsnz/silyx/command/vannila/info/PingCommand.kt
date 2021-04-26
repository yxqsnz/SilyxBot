/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.silyx.command.vannila.info

import dev.yxqsnz.classes.command.CommandContext
import dev.yxqsnz.classes.command.TextCommand

class PingCommand : TextCommand(Options) {
	companion object Options : TextCommand.Options("ping") {
	
	}
	
	override suspend fun execute(context: CommandContext) {
		with(context) {
			message.reply(
				"""
				:ping_pong: Pong!
				🔹 | Gateway: `${message.jda.gatewayPing}ms`
				🔹 | Discord Api: `${message.jda.restPing.complete()}ms`
			""".trimIndent()
			).queue()
		}
	}
}