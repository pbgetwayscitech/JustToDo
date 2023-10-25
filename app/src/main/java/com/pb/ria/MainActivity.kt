package com.pb.ria

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val new_task_button : FloatingActionButton = findViewById(R.id.new_task_floating_aciton_button)
        new_task_button.setOnClickListener {
            val i  = Intent(this@MainActivity, new_and_edit_task::class.java)
            startActivity(i)
            overridePendingTransition(com.google.android.material.R.anim.abc_slide_in_bottom, com.google.android.material.R.anim.abc_slide_out_top)
        }
    }
}