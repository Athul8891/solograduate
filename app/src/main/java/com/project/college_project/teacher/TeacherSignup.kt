package com.project.college_project.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.college_project.MainActivity
import com.project.college_project.R
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_teacher_signup.*
import java.util.HashMap

class TeacherSignup : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var uId: String
    private val TAG = "SignupActivity"
    private lateinit var loadingSnackbar: Snackbar

    private lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_signup)


        val actionBar = supportActionBar
        actionBar!!.title = " "
        mAuth = FirebaseAuth.getInstance()

        btTrCreateAc.setOnClickListener { v->
            view = v
            signUpUser()
        }
    }


    private fun signUpUser() {
        if (etTrMob.text.toString().isEmpty()) {
            etTrMob.error = "Please Enter Mobile"
            etTrMob.requestFocus()
            return
        }

        if (etTrUser.text.toString().isEmpty()) {
            etTrUser.error = "Please Enter Username"
            etTrUser.requestFocus()
            return
        }

        if (etTrEmail.text.toString().isEmpty()) {
            etTrEmail.error = "Please enter email"
            etTrEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(etTrEmail.text.toString()).matches()) {
            etTrEmail.error = "Please enter valid email"
            etTrEmail.requestFocus()
            return
        }

        if (etTrPass.text.toString().isEmpty() ) {
            etTrPass.error = "Please enter password"
            etTrPass.requestFocus()
            return
        }

        if (etTrRePass.text.toString().isEmpty() ) {
            etTrRePass.error = "Please enter password"
            etTrRePass.requestFocus()
            return
        }

        if (etTrRePass.text.toString() != etTrRePass.text.toString() ) {
            etTrRePass.error = "Please enter Correct password"
            etTrRePass.requestFocus()
            return
        }
        if (spTrSem.selectedItem.toString().equals("SUBJECT *") ) {
            Snackbar.make(
                view,
                "Subject not selected!",
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        if (spTrSub.selectedItem.toString().equals("SEMESTER *") ) {
            Snackbar.make(
                view,
                "Semester not selected!",
                Snackbar.LENGTH_LONG
            ).show()
            return
        }
        btTrCreateAc.visibility = View.GONE
        pbTrLogin.visibility = View.VISIBLE
        mAuth.createUserWithEmailAndPassword(etTrEmail.text.toString(), etTrPass.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    // val id1: String = mAuth.currentUser().getUid()
                    //  Log.d(TAG, user.toString())

                    postEvent()


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    Snackbar.make(
                        view,
                        "Signup Failed!",
                        Snackbar.LENGTH_LONG
                    ).show()

                }

                // ...
            }


    }


    private fun postEvent() {
        val user = mAuth.currentUser?.uid
        val db = FirebaseFirestore.getInstance()



        try {
            val details = HashMap<String, Any>()
            details.put("strUsr", etTrUser.text.toString())
            details.put("strEmail", etTrEmail.text.toString())
            details.put("strMob", etTrMob.text.toString())
            details.put("strKtu", etTrid.text.toString())
            details.put("strSem", spTrSem.selectedItem.toString())
            details.put("strSub", spTrSem.selectedItem.toString())
            details.put("strType", "TCR")
            details.put("uId", user.toString())


            db.collection("Users").document(user.toString()).set(details)
                .addOnSuccessListener { documentReference  ->
                    /* Snackbar.make(
                         addAddressLayout,
                         "Address Successfully Updated!",
                         Snackbar.LENGTH_SHORT
                     ).show()*/
                    Snackbar.make(
                        view,
                        "User Created Successfully!",
                        Snackbar.LENGTH_LONG
                    ).show()
                    pbTrLogin.visibility = View.GONE

                    btTrCreateAc.visibility = View.VISIBLE
                     finish()
                      startActivity(Intent(this, MainActivity::class.java))


                }.addOnFailureListener { exception: java.lang.Exception ->
                    //  Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                    pbTrLogin.visibility = View.GONE

                    btTrCreateAc.visibility = View.VISIBLE
                }
        } catch (e: Exception) {
            Toast.makeText(this, "Failed :-( !", Toast.LENGTH_LONG).show()
            pbTrLogin.visibility = View.GONE

            btTrCreateAc.visibility = View.VISIBLE

        }




    }
}