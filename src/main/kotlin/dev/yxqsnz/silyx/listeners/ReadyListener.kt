/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.silyx.listeners

import dev.yxqsnz.worker.WorkerController
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.EventListener

class ReadyListener : EventListener {
    override fun onEvent(event: GenericEvent) {
        if (event is ReadyEvent) {
            println("Eu estou pronto!")
            runBlocking {
                launch {
                    WorkerController.start()
                }
            }
        }
    }
}