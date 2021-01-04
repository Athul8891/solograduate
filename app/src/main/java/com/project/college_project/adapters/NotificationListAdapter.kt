package com.project.college_project.adapters

import android.content.Context
import android.content.Intent
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.college_project.Class
import com.project.college_project.ClassNoti
import com.project.college_project.R
import kotlinx.android.synthetic.main.class_item.view.*
import kotlinx.android.synthetic.main.insideclass_item.view.*
import kotlinx.android.synthetic.main.notification_item.view.*


import java.util.ArrayList
////for user list
class NotificationListAdapter (
    private val ctx: Context,
    private var clssList: ArrayList<ClassNoti>

) :
    RecyclerView.Adapter<NotificationListAdapter.BloodListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodListViewHolder {
        return BloodListViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.notification_item,
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


        holder.tvNotif.text = item.strMatter


    }

    fun addAll(
        list: ArrayList<ClassNoti>
    ) {
        clssList = list
        notifyDataSetChanged()
//            notifyItemRangeInserted(initialSize, list.size)

    }

    class BloodListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNotif =view.tvNotif


    }

}