package com.project.college_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.college_project.adapters.ClassNotiListAdapter
import com.project.college_project.adapters.NotificationListAdapter
import kotlinx.android.synthetic.main.activity_inside_class.*
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.activity_show_meterial.*

class NotificationActivity : AppCompatActivity() {

    private lateinit var vAdapter: NotificationListAdapter
    private var classList: ArrayList<ClassNoti> = ArrayList()
    lateinit var query: Query
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        pbNotif.visibility = View.VISIBLE
        rvNot.visibility = View.INVISIBLE

        val actionBar = supportActionBar
        actionBar!!.title = "Notification"

        val layoutManager = LinearLayoutManager(this)
        rvNot.layoutManager = layoutManager
        rvNot.setHasFixedSize(true)
        vAdapter = NotificationListAdapter(this, classList)
        rvNot.adapter = vAdapter
        getClassList()
    }

    private fun getClassList() {
        query = FirebaseFirestore.getInstance().collection("Notifiaction")

        query.get().addOnSuccessListener { result ->
            pbNotif.visibility = View.INVISIBLE
            rvNot.visibility = View.VISIBLE
            val userList = ArrayList<ClassNoti>()

            for (document in result) {
                val user = document.toObject(ClassNoti::class.java)
                user.id = document.id
                userList.add(user)

                Log.d("userList", "${user.id}")
            }
            //  btClose.visibility = View.GONE
            vAdapter.addAll(userList)
        }.addOnFailureListener { exception ->
            pbNotif.visibility = View.INVISIBLE
            rvNot.visibility = View.VISIBLE
            Log.w("TAGINSIDE", "Error getting documents: ", exception)
        }
    }
}