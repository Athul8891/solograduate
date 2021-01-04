package com.project.college_project.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.college_project.R
import kotlinx.android.synthetic.main.activity_add_classes.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.HashMap

class AddClassesActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var uId: String
    private val TAG = "SignupActivity"
    private lateinit var loadingSnackbar: Snackbar

    private lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_classes)

        val actionBar = supportActionBar
        actionBar!!.title = ""
        mAuth = FirebaseAuth.getInstance()

        btCrtClss.setOnClickListener { v->
            view = v
            postEvent()
        }


    }

    private fun postEvent() {

        progressBar2.visibility = View.VISIBLE

        btCrtClss.visibility = View.GONE
        var time = System.currentTimeMillis()
        if (etTrNm.text.toString().isEmpty()) {
            etTrNm.error = "Please Enter Teacher Name"
            etTrNm.requestFocus()
            return
        }

        if (etClsNm.text.toString().isEmpty()) {
            etClsNm.error = "Please Enter Class Name"
            etClsNm.requestFocus()
            return
        }

        if (etClsYr.text.toString().isEmpty()) {
            etClsYr.error = "Please enter year or class"
            etClsYr.requestFocus()
            return
        }

        val user = mAuth.currentUser?.uid
        val db = FirebaseFirestore.getInstance()



        try {
            val details = HashMap<String, Any>()
            details.put("strClssId", time.toString())
            details.put("strName", etTrNm.text.toString())
            details.put("strTitle", etClsNm.text.toString())
            details.put("strYr", etClsYr.text.toString())
            details.put("strTrId", user.toString())



            db.collection("Class").add(details)
                .addOnSuccessListener { documentReference  ->
                    /* Snackbar.make(
                         addAddressLayout,
                         "Address Successfully Updated!",
                         Snackbar.LENGTH_SHORT
                     ).show()*/
                    Snackbar.make(
                        view,
                        "Class Created Successfully!",
                        Snackbar.LENGTH_LONG
                    ).show()
                    progressBar2.visibility = View.GONE

                    btCrtClss.visibility = View.VISIBLE
                    // finish()
                    //  startActivity(Intent(this, EventActivity::class.java))


                }.addOnFailureListener { exception: java.lang.Exception ->
                    //  Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                    progressBar2.visibility = View.GONE

                    btCrtClss.visibility = View.VISIBLE
                }
        } catch (e: Exception) {
            Toast.makeText(this, "Failed :-( !", Toast.LENGTH_LONG).show()
            progressBar2.visibility = View.GONE

            btCrtClss.visibility = View.VISIBLE

        }




    }

}