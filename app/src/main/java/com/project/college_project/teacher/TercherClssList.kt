package com.project.college_project.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.college_project.Class
import com.project.college_project.R
import com.project.college_project.adapters.ClassListAdapter
import com.project.college_project.teacheradapter.TeacherClassListAdapter
import kotlinx.android.synthetic.main.activity_classroom.*
import kotlinx.android.synthetic.main.activity_tercher_clss_list.*

class TercherClssList : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    private lateinit var vAdapter: TeacherClassListAdapter
    private var classList: ArrayList<Class> = ArrayList()
    lateinit var query: Query
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tercher_clss_list)

        mAuth = FirebaseAuth.getInstance()

        progressBar4.visibility = View.VISIBLE
        rvTrClssList.visibility = View.INVISIBLE
        val actionBar = supportActionBar
        actionBar!!.title = "Your Classrooms"

        val layoutManager = LinearLayoutManager(this)
        rvTrClssList.layoutManager = layoutManager
        rvTrClssList.setHasFixedSize(true)
        vAdapter = TeacherClassListAdapter(this, classList)
        rvTrClssList.adapter = vAdapter

        getClassList()
    }


    private fun getClassList() {
        val user = mAuth.currentUser?.uid
        query = FirebaseFirestore.getInstance().collection("Class").whereEqualTo("strTrId", user.toString())

        query.get().addOnSuccessListener { result ->
            progressBar4.visibility = View.INVISIBLE
            rvTrClssList.visibility = View.VISIBLE
            val userList = ArrayList<Class>()

            for (document in result) {
                val user = document.toObject(Class::class.java)
                user.id = document.id
                userList.add(user)

                Log.d("userList", "${user.id}")
            }
            //  btClose.visibility = View.GONE
            vAdapter.addAll(userList)
        }.addOnFailureListener { exception ->
            Log.w("TAGINSIDE", "Error getting documents: ", exception)
            progressBar4.visibility = View.INVISIBLE
            rvTrClssList.visibility = View.VISIBLE
        }
    }

}