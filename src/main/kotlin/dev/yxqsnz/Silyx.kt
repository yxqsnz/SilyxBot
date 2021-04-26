/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz

import dev.yxqsnz.classes.Config
import dev.yxqsnz.logger.Logger
import dev.yxqsnz.service.CommandService
import dev.yxqsnz.service.ListenersService
import dev.yxqsnz.silyx.command.registerCommands
import dev.yxqsnz.worker.WorkerController
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

class Silyx {
	lateinit var config: Config
	lateinit var client: JDA
	private lateinit var silyxBuilder: JDABuilder
	val logger = Logger()
	fun start(config: Config) {
		this.logger.info("Silyx Apenas outro simples bot para o discord!")
		this.config = config
		CommandService.setup(this)
		registerCommands()
		WorkerController.registerAllWorkers()
		silyxBuilder = JDABuilder.createDefault(config.discord.token)
		ListenersService.register(silyxBuilder)
		WorkerController.setup(this)
		client = silyxBuilder.build()
	}
}