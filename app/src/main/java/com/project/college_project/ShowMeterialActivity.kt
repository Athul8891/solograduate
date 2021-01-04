package com.project.college_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.college_project.adapters.ClassNotiListAdapter
import com.project.college_project.adapters.MeterialListAdapter
import kotlinx.android.synthetic.main.activity_inside_class.*
import kotlinx.android.synthetic.main.activity_show_meterial.*

class ShowMeterialActivity : AppCompatActivity() {
    var passid = ""
    private lateinit var vAdapter: MeterialListAdapter
    private var classList: ArrayList<Metrials> = ArrayList()
    lateinit var query: Query
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_meterial)

        pbShowMet.visibility = View.VISIBLE
        rvMet.visibility = View.INVISIBLE

        val values: String? = intent.getStringExtra("METID")

        val actionBar = supportActionBar


        if(values == null ){

        }
        else{
            passid = values
        }

        if (passid.equals("1")){
            actionBar!!.title = "Question Papers"
           // tvMet.setText("Question Papers")
        }
        if (passid.equals("2")){
            actionBar!!.title = "Study Notes"

            //   tvMet.setText("Study Notes")
        }
        val layoutManager = LinearLayoutManager(this)
        rvMet.layoutManager = layoutManager
        rvMet.setHasFixedSize(true)
        vAdapter = MeterialListAdapter(this, classList)
        rvMet.adapter = vAdapter
        getClassList()
        btMetUp.setOnClickListener {
            startActivity(Intent(this,UploadMeterials::class.java))

        }


    }

    private fun getClassList() {
        query = FirebaseFirestore.getInstance().collection("Meterials").whereEqualTo("stdy",passid)

        query.get().addOnSuccessListener { result ->
            pbShowMet.visibility = View.INVISIBLE
            rvMet.visibility = View.VISIBLE
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
            pbShowMet.visibility = View.INVISIBLE
            rvMet.visibility = View.VISIBLE
            Log.w("TAGINSIDE", "Error getting documents: ", exception)
        }
    }
}