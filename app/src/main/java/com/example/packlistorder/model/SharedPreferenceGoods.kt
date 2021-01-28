package com.example.packlistorder.model

import android.content.Context
import com.example.packlistorder.bean.Good
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 *@author zhangzhe
 *@date 2021/1/28
 *@description
 */

object SharedPreferenceGoods {
    private val sharedPreferences by lazy { MainApplication.context.getSharedPreferences("good", Context.MODE_PRIVATE) }
    private val gson = Gson()

    fun addGood(good: Good) {
        val editor = sharedPreferences?.edit()
        val origin = sharedPreferences?.getString("GoodsList", "")
        val list: MutableList<Good>
        list = if (origin == "") {
            gson.fromJson("[]", object : TypeToken<MutableList<Good>>() {}.type)
        } else {
            gson.fromJson(origin, object : TypeToken<MutableList<Good>>() {}.type)
        }
        list.add(good)
        editor?.putString("GoodsList",gson.toJson(list))
        editor?.apply()
    }

    fun getGoodList(): MutableList<Good> {
        val origin = sharedPreferences?.getString("GoodsList", "")
        val list: MutableList<Good>
        list = if (origin == "") {
            gson.fromJson("[]", object : TypeToken<MutableList<Good>>() {}.type)
        } else {
            gson.fromJson(origin, object : TypeToken<MutableList<Good>>() {}.type)
        }
        return list
    }


    fun findGood(id: String): Boolean {
        val origin = sharedPreferences?.getString("GoodsList", "")
        val list: MutableList<Good>
        list = if (origin == "") {
            gson.fromJson("[]", object : TypeToken<MutableList<Good>>() {}.type)
        } else {
            gson.fromJson(origin, object : TypeToken<MutableList<Good>>() {}.type)
        }
        list.forEach {
            if (it.id == id) {
                return true
            }
        }
        return false
    }

    fun deleteGood(id: String) {
        val editor = sharedPreferences?.edit()
        val origin = sharedPreferences?.getString("GoodsList", "")
        val list: MutableList<Good>
        list = if (origin == "") {
            gson.fromJson("[]", object : TypeToken<MutableList<Good>>() {}.type)
        } else {
            gson.fromJson(origin, object : TypeToken<MutableList<Good>>() {}.type)
        }
        list.forEach {
            if (it.id == id) {
                list.remove(it)
            }
        }
        editor?.putString("GoodsList",gson.toJson(list))
        editor?.apply()
    }

}