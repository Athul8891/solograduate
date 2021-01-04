package com.project.college_project.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.project.college_project.NotificationActivity
import com.project.college_project.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_post_student.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.HashMap

class PostStudentActivity : AppCompatActivity() {
    var passid = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_student)
        val actionBar = supportActionBar
        actionBar!!.title = "Add"
        val values: String? = intent.getStringExtra("ITMID")
        if (values == null) {

        } else {
            passid = values
        }

        btPost.setOnClickListener {
            progressBar7.visibility = View.VISIBLE

            btPost.visibility = View.GONE

            postEvent()
        }
    }


    private fun postEvent() {
        if (tvAtndc.text.toString().isEmpty()) {
            tvAtndc.error = "Please Enter "
            tvAtndc.requestFocus()
            return
        }
        if (tvIntrnl.text.toString().isEmpty()) {
            tvIntrnl.error = "Please Enter "
            tvIntrnl.requestFocus()
            return
        }
        if (tvPerfo.text.toString().isEmpty()) {
            tvPerfo.error = "Please Enter "
            tvPerfo.requestFocus()
            return
        }
        if (tvAsses.text.toString().isEmpty()) {
            tvAsses.error = "Please Enter "
            tvAsses.requestFocus()
            return
        }
        if (tvLev.text.toString().isEmpty()) {
            tvLev.error = "Please Enter "
            tvLev.requestFocus()
            return
        }
        val db = FirebaseFirestore.getInstance()



        try {
            val details = HashMap<String, Any>()
            details.put("strAcess", tvAsses.text.toString())
            details.put("strAtnd", tvAtndc.text.toString())
            details.put("strInt", tvIntrnl.text.toString())
            details.put("strLv", tvLev.text.toString())
            details.put("strPerfo", tvPerfo.text.toString())




            db.collection("Marks").document(passid.toString()).set(details)
                .addOnSuccessListener { documentReference  ->
                    /* Snackbar.make(
                         addAddressLayout,
                         "Address Successfully Updated!",
                         Snackbar.LENGTH_SHORT
                     ).show()*/

                    progressBar7.visibility = View.GONE

                    btPost.visibility = View.VISIBLE
                    // finish()
                    //  startActivity(Intent(this, EventActivity::class.java))
                    Toast.makeText(this, "Posted !", Toast.LENGTH_LONG).show()


                }.addOnFailureListener { exception: java.lang.Exception ->
                    //  Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                    progressBar7.visibility = View.GONE

                    btPost.visibility = View.VISIBLE
                }
        } catch (e: Exception) {
            Toast.makeText(this, "Failed :-( !", Toast.LENGTH_LONG).show()
            progressBar7.visibility = View.GONE

            btPost.visibility = View.VISIBLE

        }




    }

}