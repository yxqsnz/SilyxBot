/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.silyx.command
import dev.yxqsnz.service.CommandService
import dev.yxqsnz.silyx.command.vannila.info.*
fun registerCommands() {
	CommandService.registerCommand(PingCommand())
}