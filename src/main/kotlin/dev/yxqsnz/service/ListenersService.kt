/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.service

import dev.yxqsnz.silyx.listeners.*
import net.dv8tion.jda.api.JDABuilder

object ListenersService {
	fun register(builder: JDABuilder) {
		builder.addEventListeners(MessageReceivedListener())
		builder.addEventListeners(ReadyListener())
	}
}