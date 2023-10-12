package com.rums.android_geocode

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this

        initComponents()
        setListeners()
        prepareWebView()
    }

    private fun initComponents() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        webView = findViewById(R.id.webView1)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                Toast.makeText(mContext, request.url.toString(), Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if(!swipeRefreshLayout.isRefreshing) {
                    swipeRefreshLayout.isRefreshing = true
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if(swipeRefreshLayout.isRefreshing) {
                    swipeRefreshLayout.isRefreshing = false
                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Toast.makeText(mContext, "onReceivedError${error.toString()}", Toast.LENGTH_SHORT)
                    .show()

                if(swipeRefreshLayout.isRefreshing) {
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }

    private fun setListeners() {
        swipeRefreshLayout.setOnRefreshListener {
            prepareWebView()
//            webView.reload()
        }
    }

    private fun prepareWebView() {
        val height = /*webView.height*/50
        val width = /*webView.width*/50

        webView.settings.javaScriptEnabled = true
        val url = "http://194.163.165.59/scopgenx_chart.html?item_id=1111&w=$width&h=$height"
//        val url = "file:///android_asset/html/demo_html.html"
        webView.loadUrl(url)
        webView.settings.builtInZoomControls = true
        webView.addJavascriptInterface(WebAppInterface(mContext), "Android")

    }

    override fun onBackPressed() {
//        super.onBackPressed()
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    /** Instantiate the interface and set the context.  */
    class WebAppInterface(private val mContext: Context) {

        /** Show a toast from the web page.  */
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }


}