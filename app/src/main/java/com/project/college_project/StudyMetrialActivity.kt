package com.project.college_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_study_metrial.*

class StudyMetrialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_metrial)

        val actionBar = supportActionBar
        actionBar!!.title = "Meterials"

        btQuestion.setOnClickListener {

            val intent = Intent(this, ShowMeterialActivity::class.java)
            // intent.putExtra("ITMID", feed)
            intent.putExtra("METID", "1")
            this.startActivity(intent)
        }

        btNotes.setOnClickListener {
            val intent = Intent(this, ShowMeterialActivity::class.java)
            // intent.putExtra("ITMID", feed)
            intent.putExtra("METID", "2")
            this.startActivity(intent)
        }
    }
}