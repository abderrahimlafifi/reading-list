package com.raydogs.app.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.raydogs.app.ArticleActivity
import com.raydogs.app.R
import com.raydogs.app.api.ApiClient

class NewPostWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            val lastKnownId = prefs.getInt(KEY_LAST_ID, -1)

            val latestPosts = ApiClient.api.getPosts(perPage = 5, page = 1)
            if (latestPosts.isEmpty()) return Result.success()

            val newestId = latestPosts.first().id

            if (lastKnownId == -1) {
                // First run — just record the current newest post, no notification
                prefs.edit().putInt(KEY_LAST_ID, newestId).apply()
                return Result.success()
            }

            val newPosts = latestPosts.filter { it.id > lastKnownId }
            if (newPosts.isNotEmpty()) {
                prefs.edit().putInt(KEY_LAST_ID, newestId).apply()
                showNotification(newPosts.size, newPosts.first().title.rendered, newPosts.first().link)
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private fun showNotification(count: Int, latestTitle: String, latestUrl: String) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(CHANNEL_ID, "New Articles", NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "Notifies when RayDogs publishes new articles"
        }
        nm.createNotificationChannel(channel)

        val intent = Intent(context, ArticleActivity::class.java).apply {
            putExtra(ArticleActivity.EXTRA_URL, latestUrl)
            putExtra(ArticleActivity.EXTRA_TITLE, latestTitle)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pi = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val body = if (count == 1) android.text.Html.fromHtml(latestTitle, android.text.Html.FROM_HTML_MODE_COMPACT).toString()
                   else "$count new articles published"

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("New on RayDogs 🐾")
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()

        nm.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val WORK_NAME = "raydogs_new_post_check"
        private const val CHANNEL_ID = "raydogs_new_articles"
        private const val NOTIFICATION_ID = 1001
        private const val PREFS = "raydogs_prefs"
        private const val KEY_LAST_ID = "last_post_id"
    }
}
