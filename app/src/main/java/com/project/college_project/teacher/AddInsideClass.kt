package com.project.college_project.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.college_project.R
import com.project.college_project.ResetPassActivity
import kotlinx.android.synthetic.main.activity_add_classes.*
import kotlinx.android.synthetic.main.activity_add_inside_class.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.HashMap

class AddInsideClass : AppCompatActivity() {

    var passid = ""
    private lateinit var mAuth: FirebaseAuth
    private lateinit var uId: String
    private val TAG = "SignupActivity"
    private lateinit var loadingSnackbar: Snackbar

    private lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inside_class)

        val values: String? = intent.getStringExtra("ITMID")

        val actionBar = supportActionBar
        actionBar!!.title = "Add Task Inside This Class"
        mAuth = FirebaseAuth.getInstance()
        getNam()
        if(values == null ){

        }
        else{
            passid = values
        }



        btAddTsk.setOnClickListener { v->
            view = v
           // startActivity(Intent(this, ResetPassActivity::class.java))
            postEvent()
        }

    }



    private fun getNam() {

        val user = mAuth.currentUser?.uid

        val db = FirebaseFirestore.getInstance()
        val docRe = db.collection("Users").document(user.toString())
        docRe.get().addOnSuccessListener {
            document ->
            if (document != null) {
                val strName = document.getString("strUsr")
                //Toast.makeText(this,strTyp,Toast.LENGTH_SHORT).show()
                if (strName != null) {

                    etName.text =strName.toString()
                    // Toast.makeText(this,_name,Toast.LENGTH_SHORT).show()
                }

            } else {

//                            startActivity(Intent(this, MainActivity::class.java))
//                            finish()
            }
        }
    }


    private fun postEvent() {

        progressBar3.visibility = View.VISIBLE

        btAddTsk.visibility = View.GONE
        var time = System.currentTimeMillis()
        if (etClssLink.text.toString().isEmpty()) {
            etClssLink.error = "Attach Link"
            etClssLink.requestFocus()
            return
        }

        if (etMatter.text.toString().isEmpty()) {
            etMatter.error = "Please Matter"
            etMatter.requestFocus()
            return
        }

        if (etDt.text.toString().isEmpty()) {
            etDt.error = "Add Date Or Time"
            etDt.requestFocus()
            return
        }


        if (etName.text.toString().isEmpty()) {
            etName.error = "Add Teacher Name"
            etName.requestFocus()
            return
        }

        val user = mAuth.currentUser?.uid
        val db = FirebaseFirestore.getInstance()



        try {
            val details = HashMap<String, Any>()
            details.put("strClssId", passid.toString())
            details.put("strDate", etDt.text.toString())
            details.put("strLink", etClssLink.text.toString())
            details.put("strMatter", etMatter.text.toString())
            details.put("strName", etName.text.toString())

            details.put("strTrId", user.toString())



            db.collection("ClassNotification").add(details)
                .addOnSuccessListener { documentReference  ->
                    /* Snackbar.make(
                         addAddressLayout,
                         "Address Successfully Updated!",
                         Snackbar.LENGTH_SHORT
                     ).show()*/

                    Toast.makeText(this, "Task Created Successfully!", Toast.LENGTH_LONG).show()

                    progressBar3.visibility = View.GONE

                    btAddTsk.visibility = View.VISIBLE
                    // finish()
                    //  startActivity(Intent(this, EventActivity::class.java))


                }.addOnFailureListener { exception: java.lang.Exception ->
                    //  Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                    progressBar3.visibility = View.GONE

                    btAddTsk.visibility = View.VISIBLE
                }
        } catch (e: Exception) {
            Toast.makeText(this, "Failed :-( !", Toast.LENGTH_LONG).show()
            progressBar3.visibility = View.GONE

            btAddTsk.visibility = View.VISIBLE

        }




    }

}