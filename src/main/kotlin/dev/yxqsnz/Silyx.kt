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
import dev.yxqsnz.database.Client as _db
import dev.yxqsnz.service.BanService as bans

class Silyx {
    lateinit var config: Config
    lateinit var client: JDA

    private lateinit var silyxBuilder: JDABuilder
    val logger = Logger()
    val database = _db()

    fun start(config: Config) {
        this.logger.info("Silyx Apenas outro simples bot para o discord!")
        this.config = config
        CommandService.setup(this)
        registerCommands()
        WorkerController.registerAllWorkers()
        silyxBuilder = JDABuilder.createDefault(config.discord.token)
        ListenersService.register(silyxBuilder)
        WorkerController.setup(this)
        logger.info("Conectando ao banco-de-dados...")
        try {
            database.connect(config.database.mongo_uri)
            logger.success("Conectado ao mongodb.")
        } catch (e: Exception) {
            logger.panic(e)
        }
        logger.info("Carregando bans...")
        bans.setup(database)
        val bansCount = bans.loadBans()
        logger.success("$bansCount usuários estão banidos do bot!")

        client = silyxBuilder.build()
    }
}
