package com.pb.ria

import android.app.Activity
import android.content.Context
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

class manage_sql_database(mcontext : Context, mactivity : Activity, mkey : String) : Thread() {

    val activity  = mactivity
    val context  =  mcontext
    val key = mkey
    lateinit var title_text: TextInputEditText
    lateinit var desccription_text : TextInputEditText
    lateinit var save : Button
    lateinit var done : Button
    lateinit var delete : Button

    override fun run() {
        super.run()


        title_text = activity.findViewById(R.id.task_edittext_title)
        desccription_text = activity.findViewById(R.id.task_edittext_description)
        save = activity.findViewById(R.id.task_btton_save)
        done = activity.findViewById(R.id.task_button_done)
        delete = activity.findViewById(R.id.task_button_delete)

        val note_db  =  Room.databaseBuilder(context, ndb::class.java, "notedb").build()
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy/HH:mm:ss", Locale.getDefault())
        val currentDateAndTime = simpleDateFormat.format(Date())

        CoroutineScope(Dispatchers.IO).launch {

            if (key.trim() != "" && key != "null") {
                // key is not ""
                Log.d("m_", "key after null check is $key")

                title_text.setText(note_db.ndao().search_in_notest(key).get(0).Title)
                desccription_text.setText(note_db.ndao().search_in_notest(key).get(0).Description)

                save.setOnClickListener {

                    val mnen = nen()
                    mnen.id = note_db.ndao().search_in_notest(key).get(0).id
                    mnen.Title = title_text.text.toString()
                    mnen.Description = desccription_text.text.toString()
                    mnen.Timestamp = currentDateAndTime.toString()
                    mnen.state = "saved"
                    note_db.ndao().update_into_database(mnen)

                }

                done.setOnClickListener {

                    val mnen = nen()
                    mnen.id = note_db.ndao().search_in_notest(key).get(0).id
                    mnen.Title = title_text.text.toString()
                    mnen.Description = desccription_text.text.toString()
                    mnen.Timestamp = currentDateAndTime.toString()
                    mnen.state = "compleated"
                    note_db.ndao().update_into_database(mnen)

                }

                delete.setOnClickListener {
                    note_db.ndao().deletenote(key)
                    Snackbar.make(context, save, "Note Deleted !", Snackbar.LENGTH_LONG).show()
                    activity.finish()
                }

            } else {
                // key is ""
                save.setOnClickListener {
                    val mnen = nen()
                    mnen.Title = title_text.text.toString()
                    mnen.Description = desccription_text.toString()
                    mnen.Timestamp = currentDateAndTime
                    mnen.state = "saved"
                    note_db.ndao().insert_note(mnen)

                }

                done.setOnClickListener {

                    val mnen = nen()
                    mnen.Title = title_text.text.toString()
                    mnen.Description = desccription_text.toString()
                    mnen.Timestamp = currentDateAndTime
                    mnen.state = "compleated"
                    note_db.ndao().insert_note(mnen)
                }

                delete.setOnClickListener {
                    Snackbar.make(
                        context,
                        save,
                        "This note has not been created yet",
                        Snackbar.LENGTH_LONG
                    ).show()
                    activity.finish()
                }
            }

        }

    }

}