/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.service

import dev.yxqsnz.Silyx
import dev.yxqsnz.cache.commands
import dev.yxqsnz.classes.command.CommandContext
import dev.yxqsnz.classes.command.TextCommand
import dev.yxqsnz.common.Colors
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import dev.yxqsnz.service.BanService as bans

object CommandService {
    private lateinit var prefix: String
    private lateinit var owners: List<String>
    private lateinit var silyx: Silyx
    operator fun get(messageContent: String): TextCommand? {
        var vanillaCommand = commands.firstOrNull { it.options.aliases.contains(messageContent.toLowerCase()) }
        if (vanillaCommand == null) vanillaCommand =
            commands.firstOrNull { it.options.name.equals(messageContent, ignoreCase = true) }
        return vanillaCommand
    }

    fun registerCommand(command: TextCommand) =
        commands.add(command)

    fun setup(silyx: Silyx) {
        prefix = silyx.config.discord.prefix
        owners = silyx.config.discord.bot_owners
        this.silyx = silyx
    }

    suspend fun exec(event: MessageReceivedEvent) {
        if (event.message.author.isBot || !event.message.contentRaw.toLowerCase().startsWith(prefix)) return
        val args = event.message.contentRaw.substring(prefix.length, event.message.contentRaw.length).trim().split(" ")
        val command = this[args[0]] ?: return
        if (listOf("XMan", "Beth").contains(bans.isUserBanned(event.message.author.id))) return

        if (command.options.guildOnly && !event.channelType.isGuild) {
            event.message.reply("❌ | Esse comando só pode ser executado em uma guilda.")
            return
        }
        if (command.options.onlyOwners && !owners.contains(event.author.id)) {
            event.message.reply("❌ | Desculpe mas esse comando só pode ser executado por pessoas especiais.")
            return
        }
        val context = CommandContext(
            silyx,
            event.message,
            event.channel,
            event,
            args.drop(1)
        )
        try {
            command.execute(context)
        } catch (e: Exception) {
            e.message?.let { silyx.logger.error(it) }
            val errorEmbed = EmbedBuilder()
            errorEmbed.setTitle("Ocorreu um erro ao executar esse comando!")
            errorEmbed.setTitle("Me desculpe mas ocorreu um erro ao executar esse comando!")
            errorEmbed.setColor(Colors.ROBLOX_RED)
            errorEmbed.addField(
                "Erro", """
				```kt
				${e.message}
				```
			""".trimIndent(), true
            )
            event.message.reply(errorEmbed.build()).queue()
        }


    }
}