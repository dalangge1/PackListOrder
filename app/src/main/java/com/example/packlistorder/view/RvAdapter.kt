package com.example.packlistorder.view

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

class RvAdapter(val context: Context) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private val list = mutableListOf<Good>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_id = itemView.findViewById<TextView>(R.id.tv_id)
        val tv_person = itemView.findViewById<TextView>(R.id.tv_person)
        val tv_time = itemView.findViewById<TextView>(R.id.tv_time)
        val tv_box_id = itemView.findViewById<TextView>(R.id.tv_box_id)

    }

    fun setList (list: MutableList<Good>) {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_box_id.setText(list[position].packageId)
        holder.tv_time.setText(list[position].time)
        holder.tv_person.setText(list[position].controller)
        holder.tv_id.setText(list[position].id)
    }
}