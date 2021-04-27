/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.classes

data class Discord(
    val bot_owners: List<String>,
    val token: String,
    val prefix: String
)

data class Database(
    val mongo_uri: String
)

data class Config(
    val environment: String,
    val discord: Discord,
    val database: Database
)