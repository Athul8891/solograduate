package com.project.college_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.HashMap

class SignupActivity : AppCompatActivity() {


    private lateinit var mAuth: FirebaseAuth
    private lateinit var uId: String
    private val TAG = "SignupActivity"
    private lateinit var loadingSnackbar: Snackbar

    private lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val actionBar = supportActionBar
        actionBar!!.title = " "
        mAuth = FirebaseAuth.getInstance()

        btCreateAc.setOnClickListener { v->
            view = v
            signUpUser()
        }


    }

    private fun signUpUser() {
        if (etMob.text.toString().isEmpty()) {
            etMob.error = "Please Enter Mobile"
            etMob.requestFocus()
            return
        }

        if (etUser.text.toString().isEmpty()) {
            etUser.error = "Please Enter Username"
            etUser.requestFocus()
            return
        }

        if (etEmail.text.toString().isEmpty()) {
            etEmail.error = "Please enter email"
            etEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
            etEmail.error = "Please enter valid email"
            etEmail.requestFocus()
            return
        }

        if (etPass.text.toString().isEmpty() ) {
            etPass.error = "Please enter password"
            etPass.requestFocus()
            return
        }

        if (etRePass.text.toString().isEmpty() ) {
            etRePass.error = "Please enter password"
            etRePass.requestFocus()
            return
        }

        if (etRePass.text.toString() != etPass.text.toString() ) {
            etRePass.error = "Please enter Correct password"
            etRePass.requestFocus()
            return
        }
        if (spSem.selectedItem.toString().equals("SEMESTER *") ) {
            Snackbar.make(
                    view,
                    "Semester not selected!",
                    Snackbar.LENGTH_LONG
            ).show()
            return
        }
        btCreateAc.visibility = View.GONE
        pbLogin.visibility = View.VISIBLE
        mAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etPass.text.toString())
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
                details.put("strUsr", etUser.text.toString())
                details.put("strEmail", etEmail.text.toString())
                details.put("strMob", etMob.text.toString())
                details.put("strKtu", etKtuid.text.toString())
                details.put("strSem", spSem.selectedItem.toString())
                details.put("strType", "STD")
                details.put("strStatus", "2")
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
                        pbLogin.visibility = View.GONE

                        btCreateAc.visibility = View.VISIBLE
                       // finish()
                      //  startActivity(Intent(this, EventActivity::class.java))


                    }.addOnFailureListener { exception: java.lang.Exception ->
                        //  Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                        pbLogin.visibility = View.GONE

                        btCreateAc.visibility = View.VISIBLE
                    }
            } catch (e: Exception) {
                Toast.makeText(this, "Failed :-( !", Toast.LENGTH_LONG).show()
                pbLogin.visibility = View.GONE

                btCreateAc.visibility = View.VISIBLE

            }




    }

}