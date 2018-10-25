package ng.com.litcakes.schedule_e.VieeModel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import ng.com.litcakes.schedule_e.Room.ScheduleDao
import ng.com.litcakes.schedule_e.Room.ScheduleDatabase
import ng.com.litcakes.schedule_e.Room.ScheduleEntity

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
 class ScheduleViewModel : ViewModel {

    var database: ScheduleDatabase?
    var allScheduleData: LiveData<List<ScheduleEntity>>?

    constructor(application: Application) : super(){
        database = ScheduleDatabase.getInstance(application.applicationContext)
        allScheduleData = database!!.scheduleDao().getAllAchedule()
    }


    fun getAllSchedules(): LiveData<List<ScheduleEntity>>{

        return  allScheduleData!!
    }

    fun insertSchedule(scheduleEntity: ScheduleEntity){
        insertScheduleTask().execute(scheduleEntity)
    }

    fun deleteAllSchedules(){
        deleteAllSchedulesTask().execute()
    }

    fun deleteSchedule( entity: ScheduleEntity){
        deleteScheduleTask().execute(entity)
    }

    inner class insertScheduleTask : AsyncTask<ScheduleEntity, Unit, Unit>(){
        override fun doInBackground(vararg params: ScheduleEntity?) {
            database!!.scheduleDao().addSchedule(params[0]!!)
        }
    }

    inner class deleteAllSchedulesTask: AsyncTask<Unit, Unit, Unit>(){
        override fun doInBackground(vararg params: Unit?) {
            database!!.scheduleDao().deleteAll()
        }
    }

    inner class deleteScheduleTask: AsyncTask<ScheduleEntity, Unit, Unit>(){
        override fun doInBackground(vararg params: ScheduleEntity?) {
            database!!.scheduleDao().deleteSchedule(params[0]!!)
        }
    }

}