package ng.com.litcakes.schedule_e

import android.content.Context
import com.firebase.jobdispatcher.*
import ng.com.litcakes.schedule_e.TodosService.TodoJobSevice
import ng.com.litcakes.schedule_e.Utils.Common
import java.util.concurrent.TimeUnit

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class Dispatcher {



    companion object {

        private val dispatcher_interval_time = TimeUnit.MINUTES.toSeconds(5).toInt()
        private val sync_flex_time = TimeUnit.MINUTES.toSeconds(1).toInt()
        private var initialized  = false

       fun startNotificationService(context: Context){
           if(!initialized){
               val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(context))
               val job = dispatcher.newJobBuilder()
                       .setLifetime(Lifetime.FOREVER)
                       .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                       .setService(TodoJobSevice::class.java)
                       .setTag(Common.dispatcher_ag)
                       .setReplaceCurrent(true)
                       .setTrigger(Trigger.executionWindow(dispatcher_interval_time, sync_flex_time+ dispatcher_interval_time))
                       .setRecurring(true)
                       .build()
               dispatcher.mustSchedule(job)
               initialized = true
           }


        }
    }

}