/*#############################################################################
 # Author: yxqsnz                                                             #
 # Github: https://github.com/yxqsnz                                          #
 # License: GNU GPL v3                                                        #
 #############################################################################*/

/**
 *  Ban Types
 *  Beth -> Cannot use bot
 *  TODO: XMan -> leave the guild that the banned user owns
 *  TODO: ShadowBan -> delay is high -> 10s delay
 */

package dev.yxqsnz.service

import com.mongodb.client.MongoCollection
import dev.yxqsnz.cache.bans
import dev.yxqsnz.classes.user.BannedUser
import dev.yxqsnz.database.Client
import org.bson.BsonDocument
import org.bson.BsonString
import org.bson.Document
import org.bson.types.ObjectId
import java.time.Instant

object BanService {
    private var bannedUsersCount = 0
    private lateinit var db: MongoCollection<Document>
    fun setup(database: Client) {
        this.db = database.client.getDatabase("Silyx").getCollection("bans")
    }

    fun loadBans(): Int {
        val bannedUsers = this.db.find()
        for (bannedUser in bannedUsers) {
            this.add(
                bannedUser["type"] as String,
                bannedUser["userId"] as String,
                bannedUser["reason"] as String,
                bannedUser["bannedIn"] as String
            )
            bannedUsersCount++
        }
        return bannedUsersCount
    }

    private fun add(type: String, userId: String, reason: String, bannedIn: String) {
        val user = BannedUser(type, userId)
        user.reason = reason
        user.bannedIn = bannedIn
        bans.add(user)
    }

    fun banUser(
        type: String,
        userId: String,
        reason: String,
        bannedIn: String = Instant.now().toEpochMilli().toString()
    ) {
        val user = BannedUser(type, userId)

        user.reason = reason
        user.bannedIn = bannedIn
        // add to cache
        add(user.type, user.userId, user.reason, user.bannedIn)
        // add to database
        val document = Document("_id", ObjectId())
        document.append("userId", userId)
            .append("type", type)
            .append("reason", reason)
            .append("bannedIn", bannedIn)

        this.db.insertOne(document)

    }

    fun isUserBanned(userId: String): String? {
        val user = bans.firstOrNull { it.userId == userId }
            ?: return null
        return user.type
    }

    fun removeUser(userId: String) {
        val user = getUser(userId)

        bans.remove(user)
        val query = BsonDocument()
        query.append("userId", BsonString(userId))
        this.db.findOneAndDelete(query)
    }

    fun getUser(userId: String) = bans.firstOrNull { it.userId == userId }
        ?: throw Exception("Esse usuário não está banido.")
}