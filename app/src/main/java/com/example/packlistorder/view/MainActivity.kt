package com.example.packlistorder.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.packlistorder.model.MainApplication
import com.example.packlistorder.R
import com.example.packlistorder.model.SharedPreferenceGoods
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextTextPersonName.setText((application as MainApplication).controllerName)
        button.setOnClickListener {
            (application as MainApplication).controllerName = editTextTextPersonName.text.toString()
            startActivity(Intent(this, ActivityMainView::class.java))
            Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show()
            (application as MainApplication).save()
            finish()
        }
    }
}