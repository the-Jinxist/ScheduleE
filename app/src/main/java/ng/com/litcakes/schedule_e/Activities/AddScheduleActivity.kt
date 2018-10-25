package ng.com.litcakes.schedule_e.Activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_add_schedule.*
import ng.com.litcakes.schedule_e.R
import ng.com.litcakes.schedule_e.Room.ScheduleEntity
import ng.com.litcakes.schedule_e.Utils.Common
import ng.com.litcakes.schedule_e.VieeModel.ScheduleViewModel
import ng.com.litcakes.schedule_e.VieeModel.ScheduleViewModelFactory
import java.util.*

class AddScheduleActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener
        , TimePickerDialog.OnTimeSetListener{

    private var init_day: Int = 0
    var init_month: Int = 0
    var init_year: Int = 0
    var init_hour: Int = 0
    var init_min: Int = 0
    private var final_day: Int = 0
    private var final_month: Int = 0
    var final_year: Int = 0
    var final_hout: Int = 0
    var final_min: Int = 0
    var priority: String? = null
    var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_schedule)

        val factory = ScheduleViewModelFactory(application)
        val viewmodel = ViewModelProviders.of(this, factory).get(ScheduleViewModel::class.java)

        select_date.setOnClickListener {
            val c = Calendar.getInstance()
            init_day = c.get(Calendar.DAY_OF_WEEK)
            init_month = c.get(Calendar.MONTH)
            init_year = c.get(Calendar.YEAR)
            init_hour = c.get(Calendar.HOUR)
            init_min = c.get(Calendar.MINUTE)

            val dialog = DatePickerDialog(
                    this@AddScheduleActivity, this@AddScheduleActivity,
                    init_year, init_month, init_day)
            dialog.show()
        }


        close_add_schedule.setOnClickListener { finish() }

        add_schedule_btn.setOnClickListener {
            if(!TextUtils.isEmpty(add_schedule_title_edit.text.toString()) && priority != null && date!= null){

                val entity = ScheduleEntity(null,add_schedule_title_edit.text.toString(), priority, date)
                viewmodel.insertSchedule(entity)
                startActivity(Intent(this@AddScheduleActivity, MainActivity::class.java))


            }
        }

   
        val highPriority: TextView = findViewById(R.id.add_schedule_high_priority)
        val lowPriority: TextView = findViewById(R.id.add_schedule_low_priority)
        val mediumPriority: TextView = findViewById(R.id.add_schedule_medium_priority)

        highPriority.setOnClickListener { v ->
            priority = Common.PRIORITY_HIGH

            highPriority.setBackgroundResource(R.drawable.text_bg)
            lowPriority.setBackgroundResource(R.drawable.priorities_drawable)
            mediumPriority.setBackgroundResource(R.drawable.priorities_drawable)
        }


        lowPriority.setOnClickListener { v ->
            priority = Common.PRIORITY_LOW

            highPriority.setBackgroundResource(R.drawable.priorities_drawable)
            lowPriority.setBackgroundResource(R.drawable.text_bg)
            mediumPriority.setBackgroundResource(R.drawable.priorities_drawable)

        }


        mediumPriority.setOnClickListener { v ->
            priority = Common.PRIORITY_MEDIUM

            highPriority.setBackgroundResource(R.drawable.priorities_drawable)
            lowPriority.setBackgroundResource(R.drawable.priorities_drawable)
            mediumPriority.setBackgroundResource(R.drawable.text_bg)
        }

    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        final_day = dayOfMonth
        final_month = month + 1
        final_year = year


        val dialog = TimePickerDialog(this@AddScheduleActivity,
                this@AddScheduleActivity, init_hour, init_min, true)
        dialog.show()

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        final_hout = hourOfDay
        final_min =minute

        val final_output =  final_day.toString() + " / " + final_month.toString() + " / " + final_year.toString()
        date = final_output
        add_schedule_date_edit.setText(final_output)

        //TODO Implement another authentication method apart from firebase auth

    }
}
