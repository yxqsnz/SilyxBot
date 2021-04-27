/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/
package dev.yxqsnz.worker

import dev.yxqsnz.Silyx
import dev.yxqsnz.worker.vanilla.UpdateStatusWorker
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object WorkerController {
    private lateinit var bot: Silyx
    private val workerManager = WorkerManager()
    fun registerAllWorkers() {
        workerManager.registerWorker(UpdateStatusWorker())
    }

    fun setup(bot: Silyx) {
        this.bot = bot
    }

    suspend fun start() {
        bot.logger.info("Iniciando ${workerManager.workers.size} workers...")

        for (worker in workerManager.workers) {
            bot.logger.debug("Iniciando worker: ${worker.workerName}")
            val context = WorkerContext(bot)
            worker.context = context
            try {
                fun execWorker() = runBlocking {
                    launch {
                        worker.exec()
                    }
                }

                val thread = Thread(fun() {
                    execWorker()
                })
                thread.start()
                bot.logger.success("Worker ${worker.workerName} foi iniciado com sucesso.")
            } catch (e: Exception) {
                bot.logger.error("Ocorreu um erro ao Iniciar o worker ${worker.workerName}: ${e.message}")
            }
        }

    }


}

class WorkerManager {
    val workers = hashSetOf<Worker>()
    fun registerWorker(worker: Worker) = workers.add(worker)


}

open class Worker(val workerName: String) {
    lateinit var context: WorkerContext

    open suspend fun exec() {}
}

open class WorkerContext(
    val silyx: Silyx
)
