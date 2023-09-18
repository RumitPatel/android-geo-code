package com.rums.android_geocode

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rums.android_geocode.utility.AppUtils

class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this

        setListeners()
    }

    private fun setListeners() {
        findViewById<Button>(R.id.btnClickHere).setOnClickListener {

            try {
                val latitude =
                    findViewById<EditText>(R.id.etLatitude).text.toString().trim().toDouble()
                val longitude =
                    findViewById<EditText>(R.id.etLongitude).text.toString().trim().toDouble()

                val addressString = AppUtils.getAddressFromLatLong(mContext, latitude, longitude)
                (findViewById<TextView>(R.id.tvAddress)).text = addressString
            } catch (e: Exception) {
                Toast.makeText(mContext, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }
    }
}