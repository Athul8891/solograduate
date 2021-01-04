package com.project.college_project.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.college_project.ClassNoti
import com.project.college_project.R
import com.project.college_project.UploadActivity
import com.project.college_project.adapters.ClassNotiListAdapter
import kotlinx.android.synthetic.main.activity_inside_class.*

class TeacherClassInside : AppCompatActivity() {

    private lateinit var vAdapter: ClassNotiListAdapter
    private var classList: ArrayList<ClassNoti> = ArrayList()
    lateinit var query: Query
    var passid = ""
    var passname = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inside_class)


        floatingActionButton.visibility = View.INVISIBLE
        pbInsideClass.visibility = View.VISIBLE
        rvClssNoti.visibility = View.INVISIBLE

        val actionBar = supportActionBar
        actionBar!!.title = " "
        val values: String? = intent.getStringExtra("ITMID")
        val names: String? = intent.getStringExtra("ITMNAME")

        tvClass.text = names.toString()

        floatingActionButton.setOnClickListener{
            startActivity(Intent(this, UploadActivity::class.java))

        }
        if(values == null ){

        }
        else{
            passid = values
        }
        val layoutManager = LinearLayoutManager(this)
        rvClssNoti.layoutManager = layoutManager
        rvClssNoti.setHasFixedSize(true)
        vAdapter = ClassNotiListAdapter(this, classList)
        rvClssNoti.adapter = vAdapter
        getClassList()
    }

    private fun getClassList() {
        query = FirebaseFirestore.getInstance().collection("ClassNotification").whereEqualTo("strClssId",passid)

        query.get().addOnSuccessListener { result ->
            pbInsideClass.visibility = View.INVISIBLE
            rvClssNoti.visibility = View.VISIBLE
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
            pbInsideClass.visibility = View.INVISIBLE
            rvClssNoti.visibility = View.VISIBLE
            Log.w("TAGINSIDE", "Error getting documents: ", exception)
        }
    }
}