package ng.com.litcakes.schedule_e.Room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
@Entity(tableName = "schedule")
data class ScheduleEntity(@PrimaryKey(autoGenerate = true)
                      @ColumnInfo(name = "id") var id: Long?,
                      @ColumnInfo(name = "scheduleTitle") var scheduleTitle: String?,
                      @ColumnInfo(name = "schedulePriority") var schedulePriority: String?,
                      @ColumnInfo(name = "scheduleDate") var scheduleDate: String?) {
constructor():this(null, "", "", "")

}