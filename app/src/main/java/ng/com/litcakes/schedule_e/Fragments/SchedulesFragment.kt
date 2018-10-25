package ng.com.litcakes.schedule_e.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_schedule.*
import ng.com.litcakes.schedule_e.Activities.AddScheduleActivity
import ng.com.litcakes.schedule_e.Adapter.ScheduleAdapter
import ng.com.litcakes.schedule_e.R
import ng.com.litcakes.schedule_e.Room.ScheduleEntity
import ng.com.litcakes.schedule_e.SchedulesObserver
import ng.com.litcakes.schedule_e.Utils.Common
import ng.com.litcakes.schedule_e.VieeModel.ScheduleViewModel
import ng.com.litcakes.schedule_e.VieeModel.ScheduleViewModelFactory

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class SchedulesFragment : Fragment() {

    lateinit var viewModel: ScheduleViewModel
    lateinit var adapter: ScheduleAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_schedule, container, false)


        val factory = ScheduleViewModelFactory(this.activity.application)
         viewModel = ViewModelProviders.of(this, factory).get(ScheduleViewModel::class.java)



        recyclerView = view.findViewById(R.id.schedule_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        val fab: FloatingActionButton = view.findViewById(R.id.add_schedule_fab)
        fab.setOnClickListener {
            startActivity(Intent(activity, AddScheduleActivity::class.java))
        }

        viewModel.getAllSchedules().observe(this, object : Observer<List<ScheduleEntity>> {
            override fun onChanged(t: List<ScheduleEntity>?) {
                 adapter = ScheduleAdapter(context, t!!)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter

                if(adapter.itemCount > 0){
                   empty_layout.visibility = View.GONE
                }else{
                    empty_layout.visibility = View.VISIBLE
                }


            }
        })

        ItemTouchHelper(RecyclerSwiper())
                .attachToRecyclerView(recyclerView)

        return  view
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.getAllSchedules().removeObservers(this)
    }

    inner class RecyclerSwiper : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {

            viewModel.deleteSchedule(adapter.getScheduleById(viewHolder!!.adapterPosition))
            adapter.notifyDataSetChanged()

            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            viewModel.deleteSchedule(adapter.getScheduleById(viewHolder!!.adapterPosition))
            adapter.notifyDataSetChanged()

        }

    }


}