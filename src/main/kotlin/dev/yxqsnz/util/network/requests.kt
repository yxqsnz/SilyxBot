package dev.yxqsnz.util.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object requests {
    private val client = OkHttpClient()
    fun get(url: String): Response {
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute()
    }

}
