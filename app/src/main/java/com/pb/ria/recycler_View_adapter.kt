package com.pb.ria

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        holder.single_title_text.text = tasks_list.get(position).Title.get(1).toString()

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy/HH:mm:ss", Locale.getDefault())
        val currentDateAndTime = simpleDateFormat.format(Date())
        if (currentDateAndTime < tasks_list.get(position).Timestamp){
            holder.task_state_view.text = "SCHEDULED"
        }
        if(currentDateAndTime == tasks_list.get(position).Timestamp){
            holder.task_state_view.text = "TODAY"
        }

        holder.date_of_task.text = tasks_list.get(position).Timestamp.toString()
        holder.title_of_task.text = tasks_list.get(position).Title
        holder.des_of_task.text = tasks_list.get(position).Description
    }

}

class recycler_View_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val single_title_text : TextView = itemView.findViewById(R.id.f_title_single_text)
    val date_of_task :TextView = itemView.findViewById(R.id.f_date)
    val task_state_view :TextView = itemView.findViewById(R.id.f_task_state)
    val title_of_task : TextView = itemView.findViewById(R.id.f_title_of_task)
    val des_of_task  :TextView = itemView.findViewById(R.id.f_description_of_task)
    init {

    }
}