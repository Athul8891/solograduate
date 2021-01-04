package com.project.college_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_pass.*

class ResetPassActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pass)



        mAuth = FirebaseAuth.getInstance()

        btnResetPassword.setOnClickListener {
            val email = edtResetEmail.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter your email!", Toast.LENGTH_SHORT).show()
            } else {
                mAuth!!.sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Check email to reset your password!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show()
                            }
                        }
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}