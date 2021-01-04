package com.project.college_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.college_project.teacher.TeacherSignup
import kotlinx.android.synthetic.main.activity_signup_type.*

class SignupType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_type)


        btTcrSignup.setOnClickListener {
            startActivity(Intent(this, TeacherSignup::class.java))

        }
        btStdSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))

        }
    }
}