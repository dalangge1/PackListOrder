package com.example.packlistorder.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.packlistorder.R
import com.example.packlistorder.model.MainApplication
import com.example.packlistorder.model.SharedPreferenceGoods
import com.example.packlistorder.utils.IdAdder
import kotlinx.android.synthetic.main.activity_config.*
import java.util.*

class ActivityConfig : AppCompatActivity() {

    lateinit var app: MainApplication

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        app = application as MainApplication

        ed_id.setText(app.originGoodId)
        ed_box_num.setText(app.everyBoxGoodsNum.toString())
        tv_controller.text = "操作员：" + app.controllerName
        ed_good_num.setText(app.total.toString())
        ed_box_id.setText(app.currentBoxNum.toString())

        bt_ok.setOnClickListener {
            ed_id.setText(ed_id.text.toString().toUpperCase(Locale.ROOT))

            // 查重
            val s = ed_id.text.toString()
            var agree = true
            for (i in 0 until ed_good_num.text.toString().toInt()) {
                if (SharedPreferenceGoods.findGood(IdAdder.add(s, i))) {
                    agree = false
                }
            }

            if (!agree) {
                Toast.makeText(this, "起始编号到终止编号中有已录入的编号（重复了），请修改！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // 无重复则
            app.originGoodId = ed_id.text.toString()
            app.everyBoxGoodsNum = ed_box_num.text.toString().toInt()
            app.total = ed_good_num.text.toString().toInt()
            app.hasPacked = 0
            app.isFinished = false
            app.save()
            startActivity(Intent(this, ActivityPacking::class.java))
            finish()
        }
    }
}