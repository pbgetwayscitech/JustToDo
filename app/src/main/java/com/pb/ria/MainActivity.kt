package com.pb.ria

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.pb.ria.db.ndb
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    lateinit var new_task_button : FloatingActionButton
    lateinit var bottom_navigation_view : BottomNavigationView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         new_task_button = findViewById(R.id.new_task_floating_aciton_button)
         bottom_navigation_view = findViewById(R.id.main_bottom_navigation_view)

        new_task_button.setOnClickListener {
            val i  = Intent(this@MainActivity, new_and_edit_task::class.java)
            startActivity(i)
            overridePendingTransition(com.google.android.material.R.anim.abc_slide_in_bottom, com.google.android.material.R.anim.abc_slide_out_top)
        }

        load_tasks_databse()
    }
    fun load_tasks_databse(){

        if (!bottom_navigation_view.isSelected){
            bottom_navigation_view.selectedItemId = R.id.menu_id_tasks
        }

        when(bottom_navigation_view.selectedItemId){
            R.id.menu_id_tasks ->
                loadlists(this, this,"saved").start()
            R.id.menu_id_compleated ->
                loadlists(this,this,"compleated").start()
            R.id.menu_id_scheduled ->
                loadlists(this,this,"saved").start()
            else ->
                false
        }

        bottom_navigation_view.setOnItemSelectedListener { item ->

            when(item.itemId){
                R.id.menu_id_tasks ->
                    loadlists(this, this,"saved").start()
                R.id.menu_id_compleated ->
                    loadlists(this,this,"compleated").start()
                R.id.menu_id_scheduled ->
                    loadlists(this,this,"saved").start()
                else ->
                    false
                //do nothing
            }

             true
        }

    }

}

class loadlists(mcontext : Context,mactivity : Activity,mstate : String) : Thread(){

    val context = mcontext
    val activity = mactivity
    val mstate = mstate
    override fun run() {
        super.run()

        val banner_no_notes = activity.findViewById<LinearLayout>(R.id.banner_no_notes_found)
        val main_recycler_View = activity.findViewById<RecyclerView>(R.id.main_recycler_view)
        Log.d("m_ria","currly loading list of $mstate")
        activity.runOnUiThread {
            banner_no_notes.visibility = View.VISIBLE
            main_recycler_View.visibility = View.INVISIBLE
        }

        val note_db  =  Room.databaseBuilder(context, ndb::class.java,
            "notedb").build()
        val all_notest = note_db.ndao().get_all_note_with_state(mstate)

        if (all_notest.size > 0){
            Log.d("m_ria","found total of ${all_notest.size} records")

            activity.runOnUiThread {
                banner_no_notes.visibility = View.INVISIBLE
                main_recycler_View.visibility = View.VISIBLE
                main_recycler_View.layoutManager = LinearLayoutManager(context)
                main_recycler_View.adapter = recycler_View_adapter(context,activity,all_notest)
            }

        }else{
            Log.d("m_","the sixe of the list is zero ${all_notest.size}")
            activity.runOnUiThread {
                banner_no_notes.visibility = View.VISIBLE
                main_recycler_View.visibility = View.INVISIBLE
            }
        }


    }

}