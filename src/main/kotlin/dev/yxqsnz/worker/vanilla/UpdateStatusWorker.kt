/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.worker.vanilla

import dev.yxqsnz.worker.Worker
import kotlinx.coroutines.delay
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity

class UpdateStatusWorker : Worker("Update status Worker") {
    override suspend fun exec() {
        with(context) {
            while (true) {
                silyx.client.presence.setPresence(OnlineStatus.IDLE, Activity.playing("OwO"))
                delay(5000)
                silyx.client.presence.setPresence(OnlineStatus.IDLE, Activity.playing("Criado pelo yxqsnz!"))
                delay(5000)
            }
        }
    }
}