package com.flipperdevices.bridge.service.impl.notification

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.flipperdevices.bridge.service.impl.R

private const val FLIPPER_NOTIFICATION_CHANNEL = "flipper_service"
const val FLIPPER_NOTIFICATION_ID = 1

class FlipperNotificationHelper(private val context: Context) {
    private val notificationBuilder =
        NotificationCompat.Builder(context, FLIPPER_NOTIFICATION_CHANNEL)
            .setContentTitle(context.getString(R.string.bridge_service_notification_title))
            .setContentText(context.getString(R.string.bridge_service_notification_desc))
            .setSmallIcon(R.drawable.ic_notification)
            .setSilent(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    private val notificationManager = NotificationManagerCompat.from(context)

    fun showStopButton() {
        notificationBuilder.addAction(
            R.drawable.ic_disconnect,
            context.getString(R.string.bridge_service_notification_action_disconnect),
            FlipperDisconnectBroadcastReceiver.getDisconnectIntent(context)
        )
        buildAndNotify()
    }

    fun showInfiniteProgressBar() {
        notificationBuilder.setProgress(0, 0, true)
        buildAndNotify()
    }

    fun show(): Notification {
        return buildAndNotify()
    }

    private fun buildAndNotify(): Notification {
        createChannelIfNotYet(context)

        val notification = notificationBuilder.build()
        notificationManager.notify(FLIPPER_NOTIFICATION_ID, notification)
        return notification
    }

    private fun createChannelIfNotYet(context: Context) {
        val notificationManager = NotificationManagerCompat.from(context)

        val flipperChannel = NotificationChannelCompat.Builder(
            FLIPPER_NOTIFICATION_CHANNEL,
            NotificationManagerCompat.IMPORTANCE_LOW
        ).setName(context.getString(R.string.bridge_service_notification_channel_name))
            .setDescription(context.getString(R.string.bridge_service_notification_channel_desc))
            .build()

        notificationManager.createNotificationChannel(flipperChannel)
    }
}