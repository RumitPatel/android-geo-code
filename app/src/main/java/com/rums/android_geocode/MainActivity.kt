package com.rums.android_geocode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        findViewById<Button>(R.id.btnClickHere).setOnClickListener{
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        }
    }
}