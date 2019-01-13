package com.mstoyan.test.red.redtestproject.ui.video

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.mstoyan.test.red.redtestproject.R
import com.mstoyan.test.red.redtestproject.core.ui.BaseFragmentInteractionListener
import com.mstoyan.test.red.redtestproject.injection.Injection

// the fragment initialization parameters
private const val ARG_VIDEO_EMBED = "no easter eggs here"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [VideoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [VideoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class VideoFragment : Fragment(), VideoContract.View {
    private var listener: BaseFragmentInteractionListener? = null
    private var presenter: VideoPresenter? = null
    private var videoEmbed = ""
    private var webPage: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoEmbed = it.getString(ARG_VIDEO_EMBED)
        }
        val repository = Injection.provideDataRepository()
        presenter = VideoPresenter(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val result = inflater.inflate(R.layout.fragment_video, container, false)
        webPage = result.findViewById(R.id.webView) as WebView
        webPage!!.settings.javaScriptEnabled = true
        webPage!!.settings.pluginState = WebSettings.PluginState.ON
        webPage!!.webChromeClient = WebChromeClient()
        webPage!!.webViewClient = WebViewClient()
        webPage!!.loadUrl(videoEmbed.replace("?id=","") + "?utm_source=www.redtube.com&utm_medium=embed-mobile&utm_campaign=embed-html5")
        return result
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as BaseFragmentInteractionListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onResume() {
        super.onResume()
        presenter!!.onViewActive(this)
    }

    override fun onPause() {
        super.onPause()
        presenter!!.onViewInactive()
    }

    override fun embedLoaded(embed: String) {
        webPage!!.loadData(embed, "text/html", "utf-8")
    }

    override fun failedLoadingEmbed() {
    }

    companion object {
        fun getBundle(embed: String): Bundle{
            val bundle = Bundle()
            bundle.putString(ARG_VIDEO_EMBED, embed)
            return bundle
        }
    }
}
