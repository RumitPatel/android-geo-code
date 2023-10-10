package com.rums.android_geocode.utility

import android.content.Context
import android.location.Geocoder
import android.text.TextUtils
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.rums.android_geocode.model.ModelInfo
import java.io.IOException
import java.util.Locale

class AppUtils {

    companion object {

    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.setImagesWithDrawable(imageView: ImageView, drawablePath: Int?) {

    Glide.with(this)
        .load(drawablePath)
        .into(imageView)
}

fun getStaticModels() {

}

fun getQuestionVideos(): ArrayList<ModelInfo?> {
    val arrVideoQuestions: ArrayList<ModelInfo?> = ArrayList<ModelInfo?>().apply {
        add(
            ModelInfo(
                "Tissue",
                2875,
                1428,
                2875 + 200,
                1428 + 200
            )
        )

        add(
            ModelInfo(
                "Cell",
                3600,
                1700,
                3600 + 200,
                1700 + 200
            )
        )

        add(
            ModelInfo(
                "Types",
                2687,
                1931,
                2687 + 200,
                1931 + 200
            )
        )

    }

    return arrVideoQuestions
}
