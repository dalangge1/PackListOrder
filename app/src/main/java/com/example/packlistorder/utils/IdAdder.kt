package com.example.packlistorder.utils

import java.lang.StringBuilder


/**
 *@author zhangzhe
 *@date 2021/1/28
 *@description
 */

object IdAdder {


    val list = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    fun add(id: String,  n: Int): String {
        var s = id
        for(i in 0 until n) {
            s = add(s)
        }
        return s
    }
    fun add(s: String): String {
        val cl = arrayListOf<Char>()
        s.forEach {
            cl.add(it)
        }
        var f = find(cl[cl.size - 1]) + 1
        if (f >= list.length) {
            f = 0
        }
        if (cl.size >= 2) {
            val c = list[f]

            if (f == 0){
                return "${add(s.substring(0, s.length - 1))}${c}"
            }
            else {
                cl[cl.size - 1] = list[f]
                return toStr(cl)
            }

        }else {
            cl[cl.size - 1] = list[f]
            return toStr(cl)
        }

    }

    fun find(c: Char): Int {
        var pos = 0
        list.forEachIndexed { index, ch -> if (ch == c) {pos = index} }
        return pos
    }
    fun toStr(array: ArrayList<Char>): String {
        val sb = StringBuilder()
        array.forEach { sb.append(it) }
        return sb.toString()
    }

}