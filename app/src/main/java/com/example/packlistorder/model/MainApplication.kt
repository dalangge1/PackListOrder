package com.example.packlistorder.model

import android.app.Application
import android.content.Context
import com.google.gson.Gson

/**
 *@author zhangzhe
 *@date 2021/1/28
 *@description
 */

class MainApplication : Application() {

    // 操作员
    var controllerName: String = ""

    // 当前产品号
    var originGoodId = ""

    // 当前箱号
    var currentBoxNum = 0

    // 每箱共装
    var everyBoxGoodsNum = 10

    // 每箱已经装多少个
    var hasPacked = 0

    // 本次总共要装
    var total = 20

    // 上次是否录入完成
    var isFinished = true


    companion object{
        lateinit var app: Application
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()
        app = this
        context = baseContext


        val sharedPreferences by lazy { context.getSharedPreferences("config", Context.MODE_PRIVATE) }

        controllerName = sharedPreferences.getString("controllerName", "")?: ""
        currentBoxNum = sharedPreferences.getInt("currentBoxNum", 0)
        originGoodId = sharedPreferences.getString("originGoodId", "ABABA543AfBB3")?: "ABABA543AfBB3"
        everyBoxGoodsNum = sharedPreferences.getInt("everyBoxGoodsNum", 10)
        hasPacked = sharedPreferences.getInt("hasPacked", 0)
        total = sharedPreferences.getInt("total", 20)
        isFinished = sharedPreferences.getBoolean("isFinished", true)

    }

    fun save() {
        val sharedPreferences by lazy { context.getSharedPreferences("config", Context.MODE_PRIVATE) }
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("controllerName", controllerName)
            putInt("currentBoxNum", currentBoxNum)
            putString("originGoodId", originGoodId)
            putInt("everyBoxGoodsNum", everyBoxGoodsNum)
            putInt("hasPacked", hasPacked)
            putInt("total", total)
            putBoolean("isFinished", isFinished)
            apply()
        }

    }

}