/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.util

import com.beust.klaxon.Klaxon

inline fun <reified T> parseJson(rawJson: String): T? {
    return Klaxon().parse<T>(rawJson)
}