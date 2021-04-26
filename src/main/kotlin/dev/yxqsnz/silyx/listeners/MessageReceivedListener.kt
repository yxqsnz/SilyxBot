/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.silyx.listeners

import dev.yxqsnz.service.CommandService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageReceivedListener : ListenerAdapter() {
	override fun onMessageReceived(event: MessageReceivedEvent) {
		runBlocking {
			launch {
				CommandService.exec(event)
			}
		}
	}
}