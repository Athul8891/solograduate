package com.project.college_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.college_project.teacheradapter.AdminAdapter
import com.project.college_project.teacheradapter.StudentAdapter
import kotlinx.android.synthetic.main.activity_student_list.*

class AdminActivity : AppCompatActivity() {
    private lateinit var vAdapter: AdminAdapter
    private var classList: ArrayList<Students> = ArrayList()
    lateinit var query: Query
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        mAuth = FirebaseAuth.getInstance()

        val actionBar = supportActionBar


        actionBar!!.title = "ADMIN ACTIVITY"

        val layoutManager = LinearLayoutManager(this)
        rvStudentList.layoutManager = layoutManager
        rvStudentList.setHasFixedSize(true)
        vAdapter = AdminAdapter(this, classList)
        rvStudentList.adapter = vAdapter
        getClassList()

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.button, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_fav -> {


            mAuth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()

            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    private fun getClassList() {
        query = FirebaseFirestore.getInstance().collection("Users")

        query.get().addOnSuccessListener { result ->

            val userList = ArrayList<Students>()

            for (document in result) {
                val user = document.toObject(Students::class.java)
                user.id = document.id
                userList.add(user)

                Log.d("userList", "${user.id}")
            }
            //  btClose.visibility = View.GONE
            vAdapter.addAll(userList)
        }.addOnFailureListener { exception ->

            Log.w("TAGINSIDE", "Error getting documents: ", exception)
        }
    }

}