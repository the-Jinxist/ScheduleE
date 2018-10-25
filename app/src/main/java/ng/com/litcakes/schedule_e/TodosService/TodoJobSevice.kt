package ng.com.litcakes.schedule_e.TodosService

import com.firebase.jobdispatcher.JobParameters
import ng.com.litcakes.schedule_e.Utils.NotificationUtils

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class TodoJobSevice : com.firebase.jobdispatcher.JobService() {
    override fun onStopJob(job: JobParameters?): Boolean {
        return false
    }

    override fun onStartJob(job: JobParameters?): Boolean {
        NotificationUtils.createNotification(this)
        return true
    }

}