package com.project.college_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.college_project.teacher.TeacherHomeActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var uId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val actionBar = supportActionBar
        actionBar!!.title = "Home"

        btClss.setOnClickListener {
            startActivity(Intent(this,ClassroomActivity::class.java))
        }
        btActivities.setOnClickListener {
            startActivity(Intent(this,StudentActivity::class.java))
        }
        btMeterial.setOnClickListener {
            startActivity(Intent(this,StudyMetrialActivity::class.java))
        }
        btNoti.setOnClickListener {
            startActivity(Intent(this,NotificationActivity::class.java))
        }
        btLogout.setOnClickListener {

            mAuth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }
    override fun onStart() {
        super.onStart()

        progressBar6.visibility = View.VISIBLE
        linearLayout.visibility = View.INVISIBLE
        btLogout.visibility = View.INVISIBLE
        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser != null) {
            val user = mAuth.currentUser?.uid

            val db = FirebaseFirestore.getInstance()
            val docRe = db.collection("Users").document(user.toString())
            docRe.get()
                    .addOnSuccessListener {
                        document ->
                        if (document != null) {
                            val strTyp = document.getString("strType")
                            //Toast.makeText(this,strTyp,Toast.LENGTH_SHORT).show()
                            if (strTyp.toString().equals("TCR")) {
//                                progressBar6.visibility = View.GONE
//                                linearLayout.visibility = View.VISIBLE
//                                btLogout.visibility = View.VISIBLE
                                startActivity(Intent(this, TeacherHomeActivity::class.java))
                                finish()
                            } else{
                                progressBar6.visibility = View.GONE
                                linearLayout.visibility = View.VISIBLE
                                btLogout.visibility = View.VISIBLE

                            }

                        } else {
                            progressBar6.visibility = View.GONE
                            linearLayout.visibility = View.VISIBLE
                            btLogout.visibility = View.VISIBLE
//                            startActivity(Intent(this, MainActivity::class.java))
//                            finish()
                        }
                    }


        }

        else {
            progressBar6.visibility = View.GONE
            linearLayout.visibility = View.VISIBLE
            btLogout.visibility = View.VISIBLE
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
