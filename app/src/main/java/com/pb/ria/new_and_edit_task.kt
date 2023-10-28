package com.pb.ria

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.pb.ria.db.ndb
import com.pb.ria.db.nen
import java.util.Date

class new_and_edit_task : AppCompatActivity() {

    lateinit var title_text: TextInputEditText
    lateinit var desccription_text : TextInputEditText
    lateinit var save : Button
    lateinit var done : Button
    lateinit var delete : Button
    var key : String = ""

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
        val input_title_key = intent.getStringExtra("title_key")+""
        if(!input_title_key.trim().toString().equals("")){
            // check_in_sql_database(input_title_key!!)

            val note_db  =  Room.databaseBuilder(this,
                ndb::class.java,
                "notedb").build()
            //val all = note_db.notedao().get_all_notes()

            key = input_title_key
        }else{
            setonclicklistner()
        }
    }

    fun check_in_sql_database( title_key : String){
        print("justtodo _checking in sql database")
        check_sql_database(this, this,title_key).start()
    }

    fun setonclicklistner(){
        print("justtodo _setting up onclick listeners")

        save.setOnClickListener {
            inset_into_sql_database(this,this,title_text.text.toString(),
                desccription_text.text.toString(),"","").start()
        }

        done.setOnClickListener {

        }

        delete.setOnClickListener {

        }

    }

}

class check_sql_database( mcontext : Context , mactivity : Activity , mkey : String) : Thread() {

    val activity  = mactivity
    val context  =  mcontext
    val key  = mkey

    override fun run() {
        super.run()

//        val note_db  =  Room.databaseBuilder(context, Note_database::class.java,const.db_name).build()

        val mlist = emptyList<nen>() // note_db.notedao().search_in_notest(key)
        if(mlist.isNotEmpty()){
            print("justodo _one note found")

            val index_zero = mlist.get(0)
            activity.runOnUiThread {
                val title_text  : TextInputEditText = activity.findViewById(R.id.task_edittext_title)
                val desccription_text  : TextInputEditText = activity.findViewById(R.id.task_edittext_description)

                title_text.setText(index_zero.Title)
                desccription_text.setText(index_zero.Description)
            }
        }else{
            activity.finish()
        }
    }
}

class inset_into_sql_database ( mcontext: Context,
                                mactivity: Activity,mtitle : String ,
                                mdesc : String,
                                mkey :String,
                                maction: String,) : Thread(){

    val activity = mactivity
    val title  = mtitle
    val context = mcontext
    val desc = mdesc
    val key =  mkey
    val action  = maction

   // val note_db  =  Room.databaseBuilder(context, Note_database::class.java,const.db_name).build()

    override fun run() {
        super.run()

        /*

        if (action == const.note_state.save){

           // val single_note = single_note_entity("",title,desc,Date().toString(),const.note_state.save.toString())
           // note_db.notedao().insert_into_note(single_note);

        }

        if (action == const.note_state.update){

           // val single_note = single_note_entity("",title,desc,Date().toString(),const.note_state.save.toString())
           // note_db.notedao().update_into_database(single_note)

        }

         */
    }
}