package vn.nguyendinhkien.appcommon.presentation.ui.web_view_demo

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.HttpAuthHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import vn.nguyendinhkien.appcommon.R
import vn.nguyendinhkien.appcommon.databinding.FragmentWebViewBinding
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseFragment
import vn.nguyendinhkien.appcommon.presentation.base.ui.QuickState
import vn.nguyendinhkien.appcommon.presentation.base.ui.QuickViewModel
import vn.nguyendinhkien.appcommon.utils.LoadingUtils


class WebViewFragment :
    BaseFragment<FragmentWebViewBinding, QuickState, QuickViewModel>(FragmentWebViewBinding::inflate) {

    override val viewModel: QuickViewModel by viewModels()
    override fun onViewReady(savedInstanceState: Bundle?) {
        binding.apply {
            webView.settings.javaScriptEnabled = true
            webView.loadUrl("https://vnext.vn")
            webView.webViewClient = object : WebViewClient(){
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    LoadingUtils.showLoading(context, false)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    LoadingUtils.hideLoading()
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    LoadingUtils.hideLoading()
                }

                override fun onReceivedHttpAuthRequest(
                    view: WebView?,
                    handler: HttpAuthHandler?,
                    host: String?,
                    realm: String?
                ) {
                    super.onReceivedHttpAuthRequest(view, handler, host, realm)
                }
            }
        }
    }

}