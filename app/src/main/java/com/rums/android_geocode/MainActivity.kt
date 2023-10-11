package com.rums.android_geocode

import android.content.Context
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this

        initComponents()
        prepareWebView()
    }

    private fun initComponents() {
        webView = findViewById(R.id.webView1)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                Toast.makeText(mContext, request.url.toString(), Toast.LENGTH_SHORT).show()
                return false
            }
        }
    }

    private fun prepareWebView() {
        webView.loadUrl("file:///android_asset/html/demo_html.html");
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}