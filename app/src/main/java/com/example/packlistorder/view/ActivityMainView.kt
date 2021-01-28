package com.example.packlistorder.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.packlistorder.R
import com.example.packlistorder.model.SharedPreferenceGoods
import kotlinx.android.synthetic.main.activity_main_view.*

class ActivityMainView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)
        rv_main.adapter = RvAdapter(this).apply {
            setList(SharedPreferenceGoods.getGoodList())
        }
        rv_main.layoutManager = LinearLayoutManager(this)
        bt_add_good.setOnClickListener {
            startActivity(Intent(this, ActivityConfig::class.java))
        }
    }
}