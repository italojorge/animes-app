//package br.com.animes.feature.main
//
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.Button
//import br.com.animes.base.feature.core.BaseFragment
//import br.com.animes.base.feature.utils.delegateproperties.navDirections
//
//class MainFragment : BaseFragment() {
//    private val navigation: MainNavigation by navDirections()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_game, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val webView = requireView().findViewById<WebView>(R.id.webview)
//        val button1 = requireView().findViewById<Button>(R.id.button)
//        val button2 = requireView().findViewById<Button>(R.id.button2)
//        val button3 = requireView().findViewById<Button>(R.id.button3)
//
//        button1.setOnClickListener {
//            webView.loadWebsite("https://www.betsul.com/busca/Gyeongnam")
////            webView.loadWebsite("https://www.bet365.com/#/AX/K^Gyeongnam/")
//        }
//        button2.setOnClickListener {
//            webView.loadWebsite("https://www.betsul.com/busca/Greeners")
////            webView.loadWebsite("https://www.bet365.com/#/AX/K^Greeners/")
//        }
//        button3.setOnClickListener {
//            webView.loadWebsite("https://www.betsul.com/busca/coreia%20do%20sul")
//        }
//
//    }
//}
//
//@SuppressLint("SetJavaScriptEnabled")
//fun WebView.loadWebsite(
//    url: String,
//    webViewClient: WebViewClient = DefaultWebViewClient()
//) {
//    settings.apply {
//        javaScriptEnabled = true
//        domStorageEnabled = true
//        builtInZoomControls = true
//    }
//    this.webViewClient = webViewClient
//
//    loadUrl(url)
//}
//
//open class DefaultWebViewClient : WebViewClient() {
//    override fun shouldOverrideUrlLoading(
//        view: WebView?,
//        url: String?
//    ) = true
//}
