package com.project.college_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.college_project.teacheradapter.StudentAdapter
import kotlinx.android.synthetic.main.activity_student_list.*

class AdminActivity : AppCompatActivity() {
    private lateinit var vAdapter: StudentAdapter
    private var classList: ArrayList<Students> = ArrayList()
    lateinit var query: Query
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)


        val actionBar = supportActionBar
        actionBar!!.title = "ADMIN ACTIVITY"

        val layoutManager = LinearLayoutManager(this)
        rvStudentList.layoutManager = layoutManager
        rvStudentList.setHasFixedSize(true)
        vAdapter = StudentAdapter(this, classList)
        rvStudentList.adapter = vAdapter
        getClassList()

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