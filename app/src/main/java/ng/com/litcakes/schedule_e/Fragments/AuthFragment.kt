package ng.com.litcakes.schedule_e.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ng.com.litcakes.schedule_e.R

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class AuthFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.auth_fragment, container, false)

        return view
    }
}