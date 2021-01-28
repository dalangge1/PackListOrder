package com.example.packlistorder.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.packlistorder.R
import com.example.packlistorder.bean.Good
import com.example.packlistorder.model.MainApplication
import com.example.packlistorder.model.SharedPreferenceGoods
import com.example.packlistorder.utils.IdAdder
import kotlinx.android.synthetic.main.activity_config.*
import kotlinx.android.synthetic.main.activity_packing.*
import java.text.SimpleDateFormat
import java.util.*

class ActivityPacking : AppCompatActivity() {

    val listNot = mutableListOf<Good>()
    val listDone = mutableListOf<Good>()
    lateinit var app: MainApplication
    val adapterNot = RvAdapter(this){}

    val adapterDone = RvAdapter(this){}

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packing)

        app = application as MainApplication

        val s = app.originGoodId
        for (i in 0 until app.total) {
            listNot.add(Good("未装箱", IdAdder.add(s, i), "无", "未装箱", "--:--:--"))
        }

        rv_not.adapter = adapterNot
        rv_not.layoutManager = LinearLayoutManager(this)
        adapterNot.refreshList(listNot)

        rv_done.adapter = adapterDone
        rv_done.layoutManager = LinearLayoutManager(this)


        bt_pack.setOnClickListener {
            val id = et_id.text.toString()
            val iterator = listNot.iterator()
            while(iterator.hasNext()){
                if (iterator.next().id == id){
                    iterator.remove()
                    listDone.add(Good("", id, app.controllerName, app.currentBoxNum.toString(), SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())))
                    adapterNot.refreshList(listNot)
                    adapterDone.refreshList(listDone.asReversed())

                    app.hasPacked++
                    if(app.hasPacked >= app.everyBoxGoodsNum){
                        app.hasPacked = 0
                        app.currentBoxNum ++
                    }



                    if (listNot.size == 0) {
                        Toast.makeText(this, "已完成录入！", Toast.LENGTH_SHORT).show()
                        listDone.forEach {
                            SharedPreferenceGoods.addGood(it)
                        }
                        app.isFinished = true
                        app.save()
                        finish()
                    }
                    return@setOnClickListener
                }
            }
            Toast.makeText(this, "产品标号不在范围中！", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("未完成本次录入时退出，将不会记录已录入的产品到数据库！")
            .setNegativeButton("取消", null)
        builder.setPositiveButton("确定退出"
        ) { _, _ ->
            super.onBackPressed()
        }.show()



    }
}