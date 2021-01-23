package com.project.college_project

import android.content.Context
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
            docRe.get().addOnSuccessListener {
                        document ->
                        if (document != null) {
                            val strTyp = document.getString("strType")
                            val strStatus = document.getString("strStatus")


                            //Toast.makeText(this,strTyp,Toast.LENGTH_SHORT).show()
                            if (strTyp.toString().equals("TCR")) {


                                if (strStatus.equals("1")){
                                    startActivity(Intent(this, TeacherHomeActivity::class.java))
                                    finish()
                                }
                                else{
                                    startActivity(Intent(this, MainActivity::class.java))

                                    finish()
                                    Toast.makeText(this,"You are not authorized to this app!",Toast.LENGTH_SHORT).show()
                                }
//                                val strSem = document.getString("strSem")
//
//                                val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
//                                var editor = sharedPreference.edit()
//                                editor.putString("strSem",strSem)
//
//                                editor.commit()
//                                progressBar6.visibility = View.GONE
//                                linearLayout.visibility = View.VISIBLE
//                                btLogout.visibility = View.VISIBLE

                            }
                            if(user.toString().equals("k0MRgQAYZlWYRwnBcbpKTuLZ6762")){

                                startActivity(Intent(this, AdminActivity::class.java))
                                finish()

                            }
                            if(strTyp.toString().equals("STD")){

                                if (strStatus.equals("1")){
                                    progressBar6.visibility = View.GONE
                                    linearLayout.visibility = View.VISIBLE
                                    btLogout.visibility = View.VISIBLE
                                }
                                else{
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                    Toast.makeText(this,"You are not authorized to this app!",Toast.LENGTH_SHORT).show()
                                }

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
