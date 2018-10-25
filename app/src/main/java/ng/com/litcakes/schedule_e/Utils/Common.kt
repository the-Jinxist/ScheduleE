package ng.com.litcakes.schedule_e.Utils

import android.content.Context
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.github.arturogutierrez.BadgesNotSupportedException
import com.github.arturogutierrez.Badges



/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class Common {
    companion object {
        val NOTIFICATION_ID = "111"
        val NOTIFICATIONCHANNELNAME = "check_todos"
        val NOTIFICATION_MANAGER_ID = 1112
        val dispatcher_ag = "tag_dispatcher"
        val PRIORITY_HIGH = "high"
        val PRIORITY_MEDIUM = "medium"
        val PRIORITY_LOW = "low"

        fun putIconBadge(context: Context, itemCount: Int){
            try {

                Badges.setBadge(context, itemCount)
            } catch (badgesNotSupportedException: BadgesNotSupportedException) {
                Log.d(TAG, badgesNotSupportedException.message)

                Toast.makeText(context, "Sorry favour, badges in tecno are not supported! ", Toast.LENGTH_SHORT)
                        .show()
            }

        }
    }
}