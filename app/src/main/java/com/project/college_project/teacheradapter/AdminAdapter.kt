package com.project.college_project.teacheradapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.college_project.*
import com.project.college_project.teacher.PostStudentActivity
import kotlinx.android.synthetic.main.class_item.view.*
import kotlinx.android.synthetic.main.studentitem.view.*


import java.util.ArrayList
////for user list
class AdminAdapter (
    private val ctx: Context,
    private var clssList: ArrayList<Students>

) :
    RecyclerView.Adapter<AdminAdapter.BloodListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodListViewHolder {
        return BloodListViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.studentitem,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return clssList.size
    }

    override fun onBindViewHolder(holder: BloodListViewHolder, position: Int) {
        val item = clssList[position]

        holder.tvTitle.text = item.strUsr

        holder.tvYear.text = item.strSem
        holder.tvStdKtu.text = item.strKtu

        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, AdminActivationActivity::class.java)
            // intent.putExtra("ITMID", feed)
            intent.putExtra("ITMID", item.uId)




            /* intent.putExtra("PRICE", "₹ ${product.sellingPrice}")
             intent.putExtra("IMGONE", product.imgOne)
             intent.putExtra("IMGTWO", product.imgTwo)
             intent.putExtra("IMGTHREE", product.imgThree)
             Toast.makeText(ctx,product.productBrand,Toast.LENGTH_SHORT).show()*/


            ctx.startActivity(intent)
        }

    }

    fun addAll(
        list: ArrayList<Students>
    ) {
        clssList = list
        notifyDataSetChanged()
//            notifyItemRangeInserted(initialSize, list.size)

    }

    class BloodListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle =view.tvStdNm
        val tvYear = view.tvStdCls
        val tvStdKtu = view.tvStdKtu


    }

}