package com.rums.android_geocode

import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.util.*

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
            getAddressFromLatLong()
        }
    }

    private fun getAddressFromLatLong() {
        val tempLat = 23.0972046
        val tempLong = 72.5404878

        try {
            val geocoder = Geocoder(mContext, Locale.getDefault())
            val addresses = geocoder.getFromLocation(tempLat, tempLong, 5)
            var addressString = ""
            for (i in addresses.indices) {
                val index = (i + 1).toString() + ""
                addressString =
                    if (index.isNotEmpty()) "$addressString\nindex: $index\n" else addressString

                val postalCode = addresses[i].postalCode
                addressString =
                    if (!postalCode.isNullOrEmpty()) "$addressString postalCode: $postalCode\n" else addressString

                val adminArea = addresses[i].adminArea
                addressString =
                    if (!adminArea.isNullOrEmpty()) "$addressString adminArea: $adminArea\n" else addressString

                val subAdminArea = addresses[i].subAdminArea
                addressString =
                    if (!subAdminArea.isNullOrEmpty()) "$addressString subAdminArea: $subAdminArea\n" else addressString

                val locality = addresses[i].locality
                addressString =
                    if (!locality.isNullOrEmpty()) "$addressString locality: $locality\n" else addressString

                val subLocality = addresses[i].subLocality
                addressString =
                    if (!subLocality.isNullOrEmpty()) "$addressString subLocality: $subLocality\n" else addressString

                val phone = addresses[i].phone
                addressString =
                    if (!phone.isNullOrEmpty()) "$addressString phone: $phone\n" else addressString
            }
            if (!TextUtils.isEmpty(addressString)) {
                (findViewById<TextView>(R.id.tvAddress)).text = addressString
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}