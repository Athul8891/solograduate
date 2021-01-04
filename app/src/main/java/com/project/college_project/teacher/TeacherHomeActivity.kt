package com.project.college_project.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.project.college_project.MainActivity
import com.project.college_project.R
import com.project.college_project.ResetPassActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_teacher_home.*
import kotlinx.android.synthetic.main.activity_teacher_home.btLogout

class TeacherHomeActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_home)
        mAuth = FirebaseAuth.getInstance()

        val actionBar = supportActionBar
        actionBar!!.title = "Teacher Home"



        btTrClss.setOnClickListener {
            startActivity(Intent(this, AddClassesActivity::class.java))

        }

        btAddtoClass.setOnClickListener {
            startActivity(Intent(this, TercherClssList::class.java))

        }

        btViewMeterial.setOnClickListener {
            startActivity(Intent(this, StudentUploads::class.java))

        }


        btViewAll.setOnClickListener {
            startActivity(Intent(this, YourClasses::class.java))

        }
        btAddActivty.setOnClickListener {
            startActivity(Intent(this, StudentList::class.java))

        }

        btLogout.setOnClickListener {

            mAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}