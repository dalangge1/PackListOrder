package com.example.packlistorder.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packlistorder.R
import com.example.packlistorder.bean.Good

/**
 *@author zhangzhe
 *@date 2021/1/28
 *@description
 */

class RvAdapter(val context: Context, val onClick: (good: Good)-> Unit) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private val list = mutableListOf<Good>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_id = itemView.findViewById<TextView>(R.id.tv_id)
        val tv_person = itemView.findViewById<TextView>(R.id.tv_person)
        val tv_time = itemView.findViewById<TextView>(R.id.tv_time)
        val tv_box_id = itemView.findViewById<TextView>(R.id.tv_box_id)

    }

    fun refreshList (list: MutableList<Good>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }

    override fun getItemCount(): Int = this.list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_goods_item, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_box_id.text = "箱号：" + list[position].packageId
        holder.tv_time.text = "时间：" + list[position].time
        holder.tv_person.text = list[position].controller
        holder.tv_id.text = list[position].id
        holder.itemView.setOnClickListener { onClick.invoke(list[position]) }
    }
}