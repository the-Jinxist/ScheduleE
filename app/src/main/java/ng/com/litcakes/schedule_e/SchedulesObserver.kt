package ng.com.litcakes.schedule_e

import android.arch.lifecycle.Observer
import android.content.Context
import ng.com.litcakes.schedule_e.Adapter.ScheduleAdapter
import ng.com.litcakes.schedule_e.Room.ScheduleEntity

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class SchedulesObserver(var context: Context,var adapter: ScheduleAdapter): Observer<List<ScheduleEntity>> {

    override fun onChanged(t: List<ScheduleEntity>?) {


    }


}