package com.project.college_project.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.college_project.Metrials
import com.project.college_project.R
import com.project.college_project.adapters.MeterialListAdapter
import kotlinx.android.synthetic.main.activity_show_meterial.*
import kotlinx.android.synthetic.main.activity_student_uploads.*

class StudentUploads : AppCompatActivity() {

    private lateinit var vAdapter: MeterialListAdapter
    private var classList: ArrayList<Metrials> = ArrayList()
    lateinit var query: Query
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_uploads)

        progressBar5.visibility = View.VISIBLE
        rvStdUpload.visibility = View.INVISIBLE


        val layoutManager = LinearLayoutManager(this)
        rvStdUpload.layoutManager = layoutManager
        rvStdUpload.setHasFixedSize(true)
        vAdapter = MeterialListAdapter(this, classList)
        rvStdUpload.adapter = vAdapter
        getClassList()


    }


    private fun getClassList() {
        query = FirebaseFirestore.getInstance().collection("Uploads")

        query.get().addOnSuccessListener { result ->
            progressBar5.visibility = View.INVISIBLE
            rvStdUpload.visibility = View.VISIBLE
            val userList = ArrayList<Metrials>()

            for (document in result) {
                val user = document.toObject(Metrials::class.java)
                user.id = document.id
                userList.add(user)

                Log.d("userList", "${user.id}")
            }
            //  btClose.visibility = View.GONE
            vAdapter.addAll(userList)
        }.addOnFailureListener { exception ->
            progressBar5.visibility = View.INVISIBLE
            rvStdUpload.visibility = View.VISIBLE
            Log.w("TAGINSIDE", "Error getting documents: ", exception)
        }
    }

}