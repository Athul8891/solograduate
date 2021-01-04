package com.project.college_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_student.*

class StudentActivity : AppCompatActivity() {
    private lateinit var uId: String
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        val actionBar = supportActionBar
        actionBar!!.title = "Student Activity"

        mAuth = FirebaseAuth.getInstance()
        uId = FirebaseAuth.getInstance().currentUser!!.uid


        checkingProfile()


    }

    private fun checkingProfile() {
        val db = FirebaseFirestore.getInstance()
        val docRe = db.collection("Marks").document(uId)
        docRe.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val teacher = document.getString("strAcess")
                    val atten = document.getString("strAtnd")
                    val intern = document.getString("strInt")
                    val leave = document.getString("strLv")
                    val perfo = document.getString("strPerfo")

                    tvAtndc.text = atten
                    tvIntrnl.text = intern
                    tvPerfo.text = perfo
                    tvAsses.text = teacher
                    tvLev.text = leave

                   // Toast.makeText(this,uId,Toast.LENGTH_SHORT).show()
                   // print(uId);


                }
                else{

                }

            }
            .addOnFailureListener { exception ->


            }
    }
}