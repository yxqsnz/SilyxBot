/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.util

import com.beust.klaxon.Klaxon
import dev.yxqsnz.classes.Config
import java.io.File
import kotlin.system.exitProcess

object ConfigFile {
	fun read(): Config? {
		val configFile = File("config.json")
		if (!configFile.exists()) {
			println("Por favor crie um arquivo chamado config.json.")
			exitProcess(1)
		}
	    return Klaxon().parse<Config>(configFile)
	}
}