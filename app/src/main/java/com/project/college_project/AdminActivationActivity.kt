package com.project.college_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_admin_activation.*
import kotlinx.android.synthetic.main.activity_home.*

class AdminActivationActivity : AppCompatActivity() {
    var passid = ""
    private lateinit var mAuth: FirebaseAuth
    private lateinit var uId: String
    private val TAG = "SignupActivity"
    private lateinit var loadingSnackbar: Snackbar

    private lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_activation)

        val actionBar = supportActionBar
        actionBar!!.title = "Control User"
        val values: String? = intent.getStringExtra("ITMID")

        if (values == null) {

        } else {
            passid = values
        }
       // Toast.makeText(this,values.toString(),Toast.LENGTH_SHORT).show()


        btActivate.setOnClickListener {
           activateUser()
        }
        btDeactivate.setOnClickListener {
            deactivateUser()
        }
        btDelete.setOnClickListener {
            deleteUser()
        }


    }



    private fun deleteUser() {
        Toast.makeText(this,passid,Toast.LENGTH_SHORT).show()
        val db = FirebaseFirestore.getInstance()

        db.collection("Users").document(passid)
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!")
                    Toast.makeText(this,"Successfully Deleted!",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e)
                    Toast.makeText(this,"Delete Failed !",Toast.LENGTH_SHORT).show()

                }
    }

    private fun deactivateUser() {
        val db = FirebaseFirestore.getInstance()

        val Ref = db.collection("Users").document(passid.toString())

// Set the "isCapital" field of the city 'DC'
        Ref.update("strStatus", "2")
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!")
                    Toast.makeText(this,"Successfully updated!",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e)
                    Toast.makeText(this,"Error updating document !",Toast.LENGTH_SHORT).show()

                }
    }

    private fun activateUser() {
        val db = FirebaseFirestore.getInstance()

        val Ref = db.collection("Users").document(passid.toString())

// Set the "isCapital" field of the city 'DC'
        Ref.update("strStatus", "1")
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!")
                Toast.makeText(this,"Successfully updated!",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e)
                    Toast.makeText(this,"Error updating document !",Toast.LENGTH_SHORT).show()

                }
    }
}