package com.pb.ria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import kotlin.concurrent.timerTask

class Flash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash)

        val task = timerTask {
            val i = Intent(this@Flash,MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
            finish()
        }

        val timer  = Timer()
        timer.schedule(task,1000)
    }
}