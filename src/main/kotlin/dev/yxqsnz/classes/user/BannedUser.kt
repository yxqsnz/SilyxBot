/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.classes.user

class BannedUser(val type: String, val userId: String) {
    var reason: String = "Motivo não especificado."
    var bannedIn: String = ""
}