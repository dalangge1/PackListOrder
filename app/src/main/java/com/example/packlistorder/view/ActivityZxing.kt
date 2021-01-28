package com.example.packlistorder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.packlistorder.R
import com.example.packlistorder.utils.ZXingUtils
import kotlinx.android.synthetic.main.activity_zxing.*

class ActivityZxing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zxing)
        imageView.setImageBitmap(ZXingUtils.createQRImage(intent.getStringExtra("content")?: "生成失败",400, 400))
    }
}