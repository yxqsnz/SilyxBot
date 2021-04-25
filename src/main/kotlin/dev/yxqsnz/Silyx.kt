/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz

import dev.yxqsnz.classes.Config
import dev.yxqsnz.logger.Logger
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import dev.yxqsnz.service.ListenersService

class Silyx {
   lateinit var config: Config
   lateinit var client: JDA
   lateinit var silyxBuilder: JDABuilder
   val logger = Logger()
   fun start(config: Config) {
      this.logger.info("Silyx Apenas outro simples bot para o discord!")
      this.config = config
      
      silyxBuilder = JDABuilder.createDefault(config.discord.token)
      ListenersService.register(silyxBuilder)
      client = silyxBuilder.build()
   }
}