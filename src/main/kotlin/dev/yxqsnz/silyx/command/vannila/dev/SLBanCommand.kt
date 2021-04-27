/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.silyx.command.vannila.dev

import dev.yxqsnz.classes.command.CommandContext
import dev.yxqsnz.classes.command.TextCommand
import dev.yxqsnz.common.Colors
import net.dv8tion.jda.api.EmbedBuilder
import java.sql.Timestamp
import java.time.Instant
import dev.yxqsnz.service.BanService as bans

class SLBanCommand : TextCommand(Options) {
    companion object Options : TextCommand.Options("SlBan") {
        override var aliases: List<String> = listOf("bl", "xb", "XBan", "sBan")
        override var onlyOwners: Boolean = true
    }

    // Sim esse comando vai ser em english
    override suspend fun execute(context: CommandContext) {
        with(context) {
            if (args.size < 2) {
                message.reply("missing action.").queue()
                return
            }
            if (listOf("b", "ban", "a", "add").contains(args[0].toLowerCase())) {
                if (args.size < 4) {
                    message.reply("usage `xb 12312312313 beth nope.`").queue()
                    return
                }
                if (bans.isUserBanned(args[1]) != null) {
                    message.reply("This user is banned cannot continue.").queue()
                    return
                }
                val type = when (args[2].toLowerCase()) {
                    "beth" -> "Beth"
                    "b" -> "Beth"
                    "x" -> "XMan"
                    "xman" -> "XMan"
                    "s" -> "ShadowBan"
                    "shandowban" -> "ShadowBan"
                    else -> "Beth"
                }
                val reason = args.asSequence().drop(0).drop(1).drop(1).drop(1).joinToString(" ")
                val userId = args[1]
                bans.banUser(type, userId, reason)

                val user = silyx.client.users.firstOrNull { it.id == userId }
                val userTag = user?.asTag ?: "User name not found."


                val resultEmbedBuilder = EmbedBuilder()
                resultEmbedBuilder.setTitle("Done.")
                resultEmbedBuilder.setAuthor(message.author.asTag, "https://youtube.com", message.author.avatarUrl)
                resultEmbedBuilder.addField("User", "$userTag($userId)", true)
                resultEmbedBuilder.addField("Type", type, true)
                resultEmbedBuilder.addField("Reason", reason, false)
                resultEmbedBuilder.setTimestamp(Instant.now())
                resultEmbedBuilder.setColor(Colors.SILYX_RED)
                message.reply(resultEmbedBuilder.build()).queue()

            } else if (listOf("r", "remover", "d").contains(args[0].toLowerCase())) {
                if (bans.isUserBanned(args[1]) == null) {
                    message.reply("This user not banned cannot continue.").queue()
                } else {
                    bans.removeUser(args[1])
                    message.reply("Banishment Removed.").queue()

                }
            } else if (listOf("v", "view", "ver").contains(args[0].toLowerCase())) {
                if (bans.isUserBanned(args[1]) == null) {
                    message.reply("This user not banned cannot continue.").queue()
                } else {
                    val userB = bans.getUser(args[1])
                    val banInfoEmbedBuilder = EmbedBuilder()
                    val user = silyx.client.users.firstOrNull { it.id == args[1] }
                    val userTag = user?.asTag ?: "User name not found."
                    val timeStamp = Timestamp(userB.bannedIn.toLong())
                    val bannedIn = timeStamp.toLocalDateTime().toString()
                    banInfoEmbedBuilder.setColor(Colors.DISCORD_BLUE)
                    banInfoEmbedBuilder.setTitle("BAN INFO")
                    banInfoEmbedBuilder.setAuthor(message.author.asTag, "https://youtube.com", message.author.avatarUrl)
                    banInfoEmbedBuilder.addField("User", "$userTag(${args[1]})", true)
                    banInfoEmbedBuilder.addField("Type", userB.type, true)
                    banInfoEmbedBuilder.addField("Banned in", bannedIn, false)
                    banInfoEmbedBuilder.setTimestamp(Instant.now())
                    banInfoEmbedBuilder.addField("Reason", userB.reason, false)

                    message.reply(banInfoEmbedBuilder.build()).queue()
                }

            } else {
                message.reply("invalid ACTION!").queue()
                return
            }
        }
    }
}