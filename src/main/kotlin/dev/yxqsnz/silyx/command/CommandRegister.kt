/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.silyx.command

import dev.yxqsnz.service.CommandService
import dev.yxqsnz.silyx.command.vannila.info.BotInfoCommand
import dev.yxqsnz.silyx.command.vannila.info.PingCommand
import dev.yxqsnz.silyx.command.vannila.misc.CatCommand
import dev.yxqsnz.silyx.command.vannila.misc.RandomWaifuCommand

fun registerCommands() {
	CommandService.registerCommand(PingCommand())
	CommandService.registerCommand(BotInfoCommand())
	CommandService.registerCommand(CatCommand())
	CommandService.registerCommand(RandomWaifuCommand())
}