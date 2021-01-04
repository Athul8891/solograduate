package com.project.college_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.college_project.adapters.ClassListAdapter
import kotlinx.android.synthetic.main.activity_classroom.*

class ClassroomActivity : AppCompatActivity() {

    private lateinit var vAdapter: ClassListAdapter
    private var classList: ArrayList<Class> = ArrayList()
    lateinit var query: Query
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classroom)

        pbClass.visibility = View.VISIBLE
        rvClssList.visibility = View.INVISIBLE
        val actionBar = supportActionBar
        actionBar!!.title = "Classroom"

        val layoutManager = LinearLayoutManager(this)
        rvClssList.layoutManager = layoutManager
        rvClssList.setHasFixedSize(true)
        vAdapter = ClassListAdapter(this, classList)
        rvClssList.adapter = vAdapter

        getClassList()

    }

    private fun getClassList() {
        query = FirebaseFirestore.getInstance().collection("Class")

        query.get().addOnSuccessListener { result ->
            pbClass.visibility = View.INVISIBLE
            rvClssList.visibility = View.VISIBLE
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
            pbClass.visibility = View.INVISIBLE
            rvClssList.visibility = View.VISIBLE
        }
    }
}