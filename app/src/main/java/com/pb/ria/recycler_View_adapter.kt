package com.pb.ria

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pb.ria.db.nen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class recycler_View_adapter(mcontext :Context,
    mactivity :Activity, list_of_tasks : List<nen> ) : RecyclerView.Adapter<recycler_View_ViewHolder>(){

    val activity = mactivity
    val context = mcontext
    val tasks_list = list_of_tasks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recycler_View_ViewHolder {
        val mview = LayoutInflater.from(parent.context).inflate(R.layout.single_main_recycler_cardview,parent,false)
        return recycler_View_ViewHolder(mview)
    }

    override fun getItemCount(): Int {
        return tasks_list.size
    }

    override fun onBindViewHolder(holder: recycler_View_ViewHolder, position: Int) {

        holder.single_title_text.text = tasks_list.get(position).Title.get(0).toString()
        holder.task_state_view.text = tasks_list.get(position).getState()

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy/HH:mm:ss", Locale.getDefault())
        val currentDateAndTime = simpleDateFormat.format(Date())

        val state_of_task = tasks_list.get(position).getState()
        val loggedtime = tasks_list.get(position).timestamp.toString()

        if(state_of_task == "saved"){

            val filter_1 = Regex("/")
            val filter_2 = Regex(":")

            val filter_current_date = currentDateAndTime.replace(filter_1,"")
            val filter_current_date_2 = filter_current_date.replace(filter_2,"")
            val filter_current_date_3 = filter_current_date_2.removeRange(8,11)

            val filter_looged_time = loggedtime.replace(filter_1,"")
            val filter_logged_time_2 = filter_looged_time.replace(filter_2,"")
            val filter_logged_time_3 = filter_logged_time_2.removeRange(8,11)

            if (filter_current_date_3 > filter_logged_time_3){
                holder.task_state_view.text = "SCHEDULED"
            }
            if(filter_current_date_3 > filter_logged_time_3){
                holder.task_state_view.text = "TODAY"
            }
        }
        holder.date_of_task.text = tasks_list.get(position).timestamp.toString()
        holder.title_of_task.text = tasks_list.get(position).title
        holder.des_of_task.text = tasks_list.get(position).description
        holder.button_done_click.setOnClickListener {
            val i  = Intent(activity, new_and_edit_task::class.java)
            i.putExtra("key",tasks_list.get(position).id.toString())
            activity.startActivity(i)
        }
        holder.layout_lin.setOnClickListener {
            val i  = Intent(activity, new_and_edit_task::class.java)
            i.putExtra("key",tasks_list.get(position).id.toString())
            activity.startActivity(i)
        }

    }

}

class recycler_View_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val single_title_text : TextView = itemView.findViewById(R.id.f_title_single_text)
    val date_of_task :TextView = itemView.findViewById(R.id.f_date)
    val task_state_view :TextView = itemView.findViewById(R.id.f_task_state)
    val title_of_task : TextView = itemView.findViewById(R.id.f_title_of_task)
    val des_of_task  :TextView = itemView.findViewById(R.id.f_description_of_task)
    val button_done_click : ImageButton = itemView.findViewById(R.id.f_done_click_button)
    val layout_lin : LinearLayout = itemView.findViewById(R.id.single_view_linear_layout)
    init {

    }
}