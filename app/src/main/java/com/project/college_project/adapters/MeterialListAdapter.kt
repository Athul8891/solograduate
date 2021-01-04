package com.project.college_project.adapters


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.project.college_project.Metrials
import com.project.college_project.R
import kotlinx.android.synthetic.main.meterial_item.view.*
import java.util.*


////for user list
class MeterialListAdapter (
    private val ctx: Context,
    private var clssList: ArrayList<Metrials>

) :
    RecyclerView.Adapter<MeterialListAdapter.BloodListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodListViewHolder {
        return BloodListViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.meterial_item,
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



        holder.tvMetTitle.text = item.strTitle


        holder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.imgThree))
            ctx.startActivity(browserIntent)




            /* intent.putExtra("PRICE", "₹ ${product.sellingPrice}")
             intent.putExtra("IMGONE", product.imgOne)
             intent.putExtra("IMGTWO", product.imgTwo)
             intent.putExtra("IMGTHREE", product.imgThree)
             Toast.makeText(ctx,product.productBrand,Toast.LENGTH_SHORT).show()*/



        }

        if (item.type.equals("1")){
            holder.ivIcon.setBackgroundResource(R.drawable.image_icon)
        }
        if (item.type.equals("2")){
            holder.ivIcon.setBackgroundResource(R.drawable.pdf_icon)
        }
    }

    fun addAll(
        list: ArrayList<Metrials>
    ) {
        clssList = list
        notifyDataSetChanged()
//            notifyItemRangeInserted(initialSize, list.size)

    }

    class BloodListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon =view.ivIcon
        val tvMetTitle = view.tvMetTitle



    }

}