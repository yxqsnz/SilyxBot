/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.classes.command

import dev.yxqsnz.Silyx
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent


class CommandContext(
	val silyx: Silyx,
	val message: Message,
	val channel: MessageChannel,
	val event: MessageReceivedEvent,
	val args: List<String>
)