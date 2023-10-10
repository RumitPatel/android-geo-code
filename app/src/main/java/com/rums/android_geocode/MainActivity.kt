package com.rums.android_geocode

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.rums.android_geocode.utility.getQuestionVideos
import com.rums.android_geocode.utility.toast


class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this

        initComponents()
    }

    private fun initComponents() {

        val imageView = findViewById<SubsamplingScaleImageView>(R.id.imageView)

        val gestureDetector = GestureDetector(mContext, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                imageView.viewToSourceCoord(e.x, e.y)?.let {

                    if(!isValidCoordinates(it.x.toInt(), it.y.toInt())) {
                        toast("Single tap: ${it.x.toInt()}, ${it.y.toInt()}")
                    }
                } ?: run {
                    toast("Single tap: Image not ready")
                }
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                imageView.viewToSourceCoord(e.x, e.y)?.let {
                    toast("Long press: ${it.x.toInt()}, ${it.y.toInt()}")
                } ?: run {
                    toast("Long press: Image not ready")
                }
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                imageView.viewToSourceCoord(e.x, e.y)?.let {
                    toast("Double tap: ${it.x.toInt()}, ${it.y.toInt()}")
                } ?: run {
                    toast("Double tap: Image not ready")
                }
                return true
            }
        })


        imageView.setImage(ImageSource.asset("lucid_jpeg.jpeg"))
        imageView.setImage(ImageSource.resource(R.drawable.lucid_jpeg))
        imageView.setOnTouchListener { _, motionEvent -> gestureDetector.onTouchEvent(motionEvent) }

    }

    private fun isValidCoordinates(x:Int, y:Int): Boolean {
        for (item in getQuestionVideos()) {
            val xStart = item?.xStart
            val yStart = item?.yStart
            val xEnd = xStart?.plus(200)
            val yEnd = yStart?.plus(200)

            if((x >= xStart!! && x <= xEnd!!) && (y >= yStart!! && y <= yEnd!!)) {
                toast(item.name.toString())
                return true
            }
        }

        /*val xStart = 2875
        val yStart = 1428
        val xEnd = xStart + 200
        val yEnd = yStart + 200

        if((x in xStart..xEnd) && (y in yStart..yEnd)) {
            return true
        }*/

        return false
    }
}