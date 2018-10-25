package ng.com.litcakes.schedule_e.Room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */

@Database(entities = arrayOf(ScheduleEntity::class), version = 1)
abstract class ScheduleDatabase: RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {
        private  var INSTANCE: ScheduleDatabase? = null


        fun getInstance(context: Context): ScheduleDatabase?{
            if(INSTANCE == null){
                synchronized(ScheduleDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ScheduleDatabase::class.java,
                            "weather.db"
                            ).build()
                }
            }

            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }



}