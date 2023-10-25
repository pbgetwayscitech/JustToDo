package com.pb.ria

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.window.OnBackInvokedDispatcher
import androidx.room.Database
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.pb.ria.db.note_database

class new_and_edit_task : AppCompatActivity() {

    lateinit var title_text: TextInputEditText
    lateinit var desccription_text : TextInputEditText
    lateinit var save : Button
    lateinit var done : Button
    lateinit var delete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_and_edit_task)

        title_text = findViewById(R.id.task_edittext_title)
        desccription_text = findViewById(R.id.task_edittext_description)
        save = findViewById(R.id.task_btton_save)
        done = findViewById(R.id.task_button_done)
        delete = findViewById(R.id.task_button_delete)

        load_if_saved()

    }

    fun load_if_saved(){
        val input_title = intent.getStringExtra("title_key")+""
        if(!input_title.toString().equals("")){
            check_in_sql_database(input_title!!)
        }else{
            setonclicklistner()
        }
    }

    fun check_in_sql_database( title_key : String){
        print("justtodo _checking in sql database")

    }

    fun setonclicklistner(){
        print("justtodo _setting up onclick listeners")
    }

}

class check_sql_database( mcontext : Context , mactivity : Activity , mkey : String) : Thread() {

    val activity  = mactivity
    val context  =  mcontext
    val key  = mkey

    val note_db  =  Room.databaseBuilder(context,note_database::class.java,const.db_name).build()

    override fun run() {
        super.run()

        val mlist = note_db.notedao().search_in_notest(key)
        if(mlist.isNotEmpty()){
            print("justodo _one note found")
        }

    }
}