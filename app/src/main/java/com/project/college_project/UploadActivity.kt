package com.project.college_project

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_upload.*
import kotlinx.android.synthetic.main.activity_upload_meterials.*

class UploadActivity : AppCompatActivity() {

    var rb = 0;
    private lateinit var view: View


    private val productStorage: StorageReference =
        FirebaseStorage.getInstance().reference.child("Uploads")

    val db = FirebaseFirestore.getInstance()
    var time = System.currentTimeMillis()

    private var imgUrisOne: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        val actionBar = supportActionBar
        actionBar!!.title = "Upload"
        btAddProduct.setOnClickListener { v->
            view = v

            if (rbImg.isChecked){
                rb = 1
            }
            if (rbDoc.isChecked){
                rb = 2
            }
            if (rb.equals(0)){
                Snackbar.make(
                    view,
                    "Please select a file type !",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            if (EtUpTitle.text.toString().isEmpty()) {
                EtUpTitle.error = "Please enter title"
                EtUpTitle.requestFocus()
                return@setOnClickListener
            }
            onBtAddProduct(view)
        }

    }

    private fun onBtAddProduct(it: View) {
        btAddProduct.visibility = View.GONE
        pbAddProduct.visibility = View.VISIBLE
        val product = HashMap<String, Any>()
        product["type"] = rb.toString()
        product["strTitle"] = EtUpTitle.text.toString()


        uploadImage(time.toString(), product, imgUrisOne, it, "imgThree")

    }

    fun setMainImage(view: View) {

        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            imgUrisOne = data?.data
            Toast.makeText(this,"File Attached",Toast.LENGTH_SHORT).show()
            ivMain.setBackgroundResource(R.drawable.ic_file)//The uri with the location of the file
        }
        else{
            Toast.makeText(this,"File Attaching failed!",Toast.LENGTH_SHORT).show()
        }
    }


    private fun uploadImage(
        filename: String,
        product: HashMap<String, Any>,
        uri: Uri?,
        v: View,
        s: String) {
        if (uri == null) {
            when (filename) {
               // "two.jpg" -> uploadImage(time.toString(), product, imgUrisOne, v, "imgOne")
                else -> Snackbar.make(v, "No File Selected", Snackbar.LENGTH_SHORT).show()
            }
        } else {
            val avatarRef = productStorage.child(product["productId"].toString()).child(filename)
            val uploadTask = avatarRef.putFile(uri)
            uploadTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    avatarRef.downloadUrl.addOnSuccessListener { uri ->
                        product[s] = uri.toString()
                        when (filename) {

                            "two.jpg" -> uploadImage(time.toString(), product, imgUrisOne, v, "imgOne")
                            else -> {
                                db.collection("Uploads")
                                    .add(product)
                                    .addOnSuccessListener {

                                        btAddProduct.visibility = View.VISIBLE
                                        Snackbar.make(v, "File Uploaded", Snackbar.LENGTH_LONG)
                                            .show()
                                        btAddProduct.visibility = View.VISIBLE
                                        pbAddProduct.visibility = View.GONE
                                      //  startActivity(Intent(this, MainActivity::class.java))
                                        // clearDetails()
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(
                                            "",
                                            "Error adding document",
                                            e
                                        )

                                        btAddProduct.visibility = View.VISIBLE
                                        pbAddProduct.visibility = View.GONE
                                    }
                            }
                        }
                    }.addOnFailureListener { exception ->
                        Log.w(
                            "",
                            "Error Uploading document",
                            exception
                        )
                        Snackbar.make(v, "Error Uploading document ", Snackbar.LENGTH_LONG)
                                .show()

                        btAddProduct.visibility = View.VISIBLE
                        pbAddProduct.visibility = View.GONE
                    }

                } else {
                    Snackbar.make(v, "Failed , File type not matching to Image/PDF ", Snackbar.LENGTH_LONG)
                            .show()
                    btAddProduct.visibility = View.VISIBLE
                    pbAddProduct.visibility = View.GONE

                }
            }
        }
    }
}