package ng.com.litcakes.schedule_e.Utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class FragmentUtils {

    companion object {

        fun replace_fragment(fragmentManager: FragmentManager, frameId: Int, fragment: Fragment) :Unit{
            fragmentManager.beginTransaction()
                    .replace(frameId, fragment)
                    .commit()
        }

    }

}