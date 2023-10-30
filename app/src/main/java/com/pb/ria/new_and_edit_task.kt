package com.pb.ria

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.pb.ria.db.ndb
import com.pb.ria.db.nen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.concurrent.thread

class new_and_edit_task : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_and_edit_task)

        load_if_saved()

    }

    fun load_if_saved(){
        val input_title_key = intent.getStringExtra("key")+"".trim()
        //manage_sql_database(this,this,input_title_key).start()
        backthread(this, this, input_title_key).start()

    }
}

class backthread(mcontext : Context, mactivity : Activity , mkey : String) : Thread(){

    val activity  = mactivity
    val context  =  mcontext
    val key = mkey
    lateinit var title_text: TextInputEditText
    lateinit var desccription_text : TextInputEditText
    lateinit var save : Button
    lateinit var done : Button
    lateinit var delete : Button

    val note_db  =  Room.databaseBuilder(mcontext, ndb::class.java, "notedb").build()

    override fun run() {
        super.run()

        val mnen = nen()
        mnen.timestamp = Date().toString()
        note_db.ndao().insert_note(mnen)

        Log.d("m_","db inserted")

        Log.d("m_","db inserte ${note_db.ndao()._all}")


    }
}


