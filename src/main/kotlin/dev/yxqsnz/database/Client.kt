/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

package dev.yxqsnz.database

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients

class Client {
    lateinit var client: MongoClient
    fun connect(uri: String) {
        val settings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(uri))
            .retryWrites(true)
            .retryReads(true)
            .build()
        client = MongoClients.create(settings)

    }
}