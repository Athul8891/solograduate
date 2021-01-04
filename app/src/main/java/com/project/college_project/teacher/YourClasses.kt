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
import kotlinx.android.synthetic.main.activity_classroom.*

class YourClasses : AppCompatActivity() {
    private lateinit var vAdapter: ClassListAdapter
    private var classList: ArrayList<Class> = ArrayList()
    lateinit var query: Query
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classroom)
        mAuth = FirebaseAuth.getInstance()


        pbClass.visibility = View.VISIBLE
        rvClssList.visibility = View.INVISIBLE
        val actionBar = supportActionBar
        actionBar!!.title = "Your Classroom"

        val layoutManager = LinearLayoutManager(this)
        rvClssList.layoutManager = layoutManager
        rvClssList.setHasFixedSize(true)
        vAdapter = ClassListAdapter(this, classList)
        rvClssList.adapter = vAdapter

        getClassList()

    }

    private fun getClassList() {
        val user = mAuth.currentUser?.uid
        query = FirebaseFirestore.getInstance().collection("Class").whereEqualTo("strTrId",user.toString())

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