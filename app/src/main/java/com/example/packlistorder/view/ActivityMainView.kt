package com.example.packlistorder.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.packlistorder.R
import com.example.packlistorder.model.SharedPreferenceGoods
import kotlinx.android.synthetic.main.activity_main_view.*

class ActivityMainView : AppCompatActivity() {

    val adapter = RvAdapter(this) { good ->
        val builder = AlertDialog.Builder(this)
        builder.setTitle("产品：" + good.id.toString())
            .setNegativeButton("确定", null)
        builder.setPositiveButton(
            "删除"
        ) { _, _ ->
            SharedPreferenceGoods.deleteGood(good.id)
            refresh()
        }.show()
    }.apply {
        refreshList(SharedPreferenceGoods.getGoodList().asReversed())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)
        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(this)
        bt_add_good.setOnClickListener {
            startActivity(Intent(this, ActivityConfig::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        refresh()
    }

    fun refresh(){
        adapter.refreshList(SharedPreferenceGoods.getGoodList().asReversed())
    }
}