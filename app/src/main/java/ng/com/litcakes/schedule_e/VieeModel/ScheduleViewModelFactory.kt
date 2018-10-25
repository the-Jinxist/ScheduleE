package ng.com.litcakes.schedule_e.VieeModel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class ScheduleViewModelFactory: ViewModelProvider.Factory {

    var application: Application? = null

    constructor(application: Application?) {
        this.application = application
    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ScheduleViewModel(application!!) as T
    }
}