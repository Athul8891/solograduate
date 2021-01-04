package com.project.college_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast

import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.college_project.teacher.TeacherHomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        val actionBar = supportActionBar
        actionBar!!.title = " "
        btForgotPassword.setOnClickListener {
            startActivity(Intent(this, SignupType::class.java))

        }


        btFgPassword.setOnClickListener {
            startActivity(Intent(this, ResetPassActivity::class.java))

        }

        btLogin.setOnClickListener {

            logIn()
        }
    }


    private fun logIn() {
        if (etUserName.text.toString().isEmpty()) {
            etUserName.error = "Please enter email"
            etUserName.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(etUserName.text.toString()).matches()) {
            etUserName.error = "Please enter valid email"
            etUserName.requestFocus()
            return
        }

        if (etPassword.text.toString().isEmpty()) {
            etPassword.error = "Please enter password"
            etPassword.requestFocus()
            return
        }

        progressBar.visibility = View.VISIBLE

        btLogin.visibility = View.INVISIBLE
        auth.signInWithEmailAndPassword(etUserName.text.toString(), etPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    progressBar.visibility = View.GONE
                    btLogin.visibility = View.VISIBLE
                    startActivity(Intent(this,HomeActivity::class.java))
                    finish()





                    // finish()


                } else {
                    progressBar.visibility = View.GONE
                    btLogin.visibility = View.VISIBLE
                    Toast.makeText(this,"Login Failed!",Toast.LENGTH_SHORT).show()
//                    Snackbar.make(
//                        view,
//                        "Login Failed!",
//                        Snackbar.LENGTH_LONG
//                    ).show()

                }
            }
    }
}