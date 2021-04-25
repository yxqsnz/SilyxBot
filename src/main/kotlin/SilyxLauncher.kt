/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

import dev.yxqsnz.Silyx
import dev.yxqsnz.util.ConfigFile

fun main() {
	val silyx = Silyx()
	ConfigFile.read()?.let { silyx.start(it) }
}