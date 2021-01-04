package com.project.college_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.college_project.Class
import com.project.college_project.InsideClassActivity
import com.project.college_project.R
import kotlinx.android.synthetic.main.class_item.view.*


import java.util.ArrayList
////for user list
class ClassListAdapter (
    private val ctx: Context,
    private var clssList: ArrayList<Class>

) :
    RecyclerView.Adapter<ClassListAdapter.BloodListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodListViewHolder {
        return BloodListViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.class_item,
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

        holder.tvTitle.text = item.strTitle

        holder.tvYear.text = item.strYr
        holder.tvTecher.text = item.strName

        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, InsideClassActivity::class.java)
            // intent.putExtra("ITMID", feed)
            intent.putExtra("ITMID", item.strClssId)
            intent.putExtra("ITMNAME", item.strTitle)



            /* intent.putExtra("PRICE", "₹ ${product.sellingPrice}")
             intent.putExtra("IMGONE", product.imgOne)
             intent.putExtra("IMGTWO", product.imgTwo)
             intent.putExtra("IMGTHREE", product.imgThree)
             Toast.makeText(ctx,product.productBrand,Toast.LENGTH_SHORT).show()*/


            ctx.startActivity(intent)
        }

    }

    fun addAll(
        list: ArrayList<Class>
    ) {
        clssList = list
        notifyDataSetChanged()
//            notifyItemRangeInserted(initialSize, list.size)

    }

    class BloodListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle =view.tvTitle
        val tvYear = view.tvYear
        val tvTecher = view.tvTecher


    }

}