/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.classes.command

abstract class TextCommand (val options: Options){
	abstract suspend fun execute(context: CommandContext)
	abstract class Options(val name: String) {
		open var description: String = "Comando sem descrição"
		open var aliases: List<String> = listOf()
		open var guildOnly: Boolean = false
		open var onlyOwners: Boolean = false
	}
}