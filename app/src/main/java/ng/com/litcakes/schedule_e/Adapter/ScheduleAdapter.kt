package ng.com.litcakes.schedule_e.Adapter

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ng.com.litcakes.schedule_e.R
import ng.com.litcakes.schedule_e.Room.ScheduleEntity
import ng.com.litcakes.schedule_e.Utils.Common

/**
 * Created by Big-Nosed Developer on the Edge of Infinity.
 */
class ScheduleAdapter( var context: Context,  var modelList: List<ScheduleEntity>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {




    interface onDeleteScheduleButtonClicked {
        fun onDeleteButtonClicked(scheduleEntity: ScheduleEntity)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.schedule_title.text = this.modelList[position].scheduleTitle

        holder.schedule_date.text = this.modelList[position].scheduleDate
        holder.schedulePriority.setOnClickListener { v ->
            Snackbar.make(v,
                    "The priory of this schedule is " +this.modelList[position].schedulePriority.toString(),
                    Snackbar.LENGTH_SHORT)
                    .show()
        }
        holder.deleteSchedule.setOnClickListener {
            val deleteButtonInterface = context as onDeleteScheduleButtonClicked
            deleteButtonInterface.onDeleteButtonClicked(this.modelList[position])

        }

        when(modelList[position].schedulePriority){

            Common.PRIORITY_HIGH -> {
                holder.schedulePriority.setImageDrawable(context.resources.getDrawable(R.drawable.ic_error_outline_red_24dp))
            }

            Common.PRIORITY_MEDIUM -> {
                holder.schedulePriority.setImageDrawable(context.resources.getDrawable(R.drawable.ic_error_outline_yellow_24dp))
            }

            Common.PRIORITY_LOW -> {
                holder.schedulePriority.setImageDrawable(context.resources.getDrawable(R.drawable.ic_error_outline_green_24dp))
            }

        }



    }

    fun getScheduleById(position: Int): ScheduleEntity{
        return modelList[position]
    }

    override fun getItemCount(): Int {
        return this.modelList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.schedule_layout, parent, false)

        return  ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val schedule_title: TextView = itemView.findViewById(R.id.schedule_layout_title)
        val schedule_date: TextView = itemView.findViewById(R.id.schedule_layout_date)
        val schedulePriority: ImageView = itemView.findViewById(R.id.schedule_priority)
        val deleteSchedule: ImageView = itemView.findViewById(R.id.delete_schedule)


    }
}