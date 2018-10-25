package ng.com.litcakes.schedule_e.Utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.media.app.NotificationCompat
import ng.com.litcakes.schedule_e.Activities.MainActivity
import ng.com.litcakes.schedule_e.R

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class NotificationUtils {

    companion object {
        fun createNotification(context: Context){

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val builder = android.support.v4.app.NotificationCompat.Builder(context, Common.NOTIFICATION_ID)
            builder.mContentTitle = "Schedule E!"
            builder.mContentText = "You have Todos waiting!"
            builder.setSmallIcon(R.drawable.note)
            builder.setContentIntent(PendingIntent.getActivities(context, 111, arrayOf(Intent(context, MainActivity::class.java)), PendingIntent.FLAG_UPDATE_CURRENT))
            builder.setAutoCancel(true)


           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               val notificationChannel = NotificationChannel(Common.NOTIFICATION_ID, Common.NOTIFICATIONCHANNELNAME, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.setShowBadge(true)
            } else {

               builder.priority = Notification.PRIORITY_HIGH

            }

            manager.notify(Common.NOTIFICATION_MANAGER_ID, builder.build())

        }
    }
}