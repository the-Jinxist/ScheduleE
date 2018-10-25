package ng.com.litcakes.schedule_e.Room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
* Created by Big-Nosed Developer on the Edge of Infinity.
*/
@Dao
interface ScheduleDao {

    @Query("SELECT * from schedule")
    fun getAllAchedule(): LiveData<List<ScheduleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSchedule(entity: ScheduleEntity)

    @Query("DELETE from schedule")
    fun deleteAll()

    @Delete
    fun deleteSchedule(entity: ScheduleEntity)

}