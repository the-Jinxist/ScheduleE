package ng.com.litcakes.schedule_e.Activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ng.com.litcakes.schedule_e.Adapter.ScheduleAdapter
import ng.com.litcakes.schedule_e.Dispatcher
import ng.com.litcakes.schedule_e.Fragments.AuthFragment
import ng.com.litcakes.schedule_e.Fragments.SchedulesFragment
import ng.com.litcakes.schedule_e.R
import ng.com.litcakes.schedule_e.Room.ScheduleEntity
import ng.com.litcakes.schedule_e.Utils.FragmentUtils
import ng.com.litcakes.schedule_e.VieeModel.ScheduleViewModel
import ng.com.litcakes.schedule_e.VieeModel.ScheduleViewModelFactory


class MainActivity : AppCompatActivity(), ScheduleAdapter.onDeleteScheduleButtonClicked {

    override fun onDeleteButtonClicked(scheduleEntity: ScheduleEntity) {
        viewmodel!!.deleteSchedule(scheduleEntity)
    }


    var viewmodel: ScheduleViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = main_toolbar
        setSupportActionBar(toolbar)

        val manager : FragmentManager = supportFragmentManager
        FragmentUtils.replace_fragment(manager, R.id.main_frame_layout, SchedulesFragment())

        Dispatcher.startNotificationService(this)


        val factory = ScheduleViewModelFactory(this.application)
         viewmodel = ViewModelProviders.of(this, factory).get(ScheduleViewModel::class.java)

        val bottomNav: BottomNavigationView = findViewById(R.id.navigation)
        bottomNav.selectedItemId = R.id.navigation_schedules

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.navigation_schedules -> {

                    var toolBarText: TextView = findViewById(R.id.tool_bar_text)
                    toolBarText.text = getString(R.string.schedules)


                    FragmentUtils.replace_fragment(manager, R.id.main_frame_layout, SchedulesFragment())

                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_authenticate ->{
                    var toolBarText: TextView = findViewById(R.id.tool_bar_text)
                    toolBarText.text = getString(R.string.account)

                    FragmentUtils.replace_fragment(manager, R.id.main_frame_layout, AuthFragment())

                    return@setOnNavigationItemSelectedListener true
                }


                else -> {
                    return@setOnNavigationItemSelectedListener  false
                }
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
       if(id == R.id.main_menu_delete_all){

           val builder = AlertDialog.Builder(this)
           val view = LayoutInflater.from(this).inflate(R.layout.delete_warning, null)
           builder.setView(view)
           val dialog: AlertDialog = builder.create()
           dialog.show()
           val sure: TextView = view.findViewById(R.id.delete_warning_sure)
           sure.setOnClickListener {
              viewmodel!!.deleteAllSchedules()
              dialog.dismiss()
           }


           val nope: TextView = view.findViewById(R.id.delete_warning_nope)
           nope.setOnClickListener {
               dialog.cancel()

           }




           return true
       }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()


    }

}
