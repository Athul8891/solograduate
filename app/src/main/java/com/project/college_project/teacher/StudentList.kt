package com.project.college_project.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.college_project.ClassNoti
import com.project.college_project.R
import com.project.college_project.Students
import com.project.college_project.adapters.NotificationListAdapter
import com.project.college_project.teacheradapter.StudentAdapter
import com.project.college_project.teacheradapter.TeacherClassListAdapter
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentList : AppCompatActivity() {
    private lateinit var vAdapter: StudentAdapter
    private var classList: ArrayList<Students> = ArrayList()
    lateinit var query: Query
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val actionBar = supportActionBar
        actionBar!!.title = "Select Student"

        val layoutManager = LinearLayoutManager(this)
        rvStudentList.layoutManager = layoutManager
        rvStudentList.setHasFixedSize(true)
        vAdapter = StudentAdapter(this, classList)
        rvStudentList.adapter = vAdapter
        getClassList()

    }

    private fun getClassList() {
        query = FirebaseFirestore.getInstance().collection("Users").whereEqualTo("strType","STD")

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