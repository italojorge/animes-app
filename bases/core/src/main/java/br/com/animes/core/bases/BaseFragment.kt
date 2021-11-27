package br.com.animes.core.bases

import android.content.Context
import androidx.fragment.app.Fragment
import br.com.animes.domain.AnalyticsEvent
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {
//    private val analyticsEvent: AnalyticsEvent by inject()

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        analyticsEvent.recordScreen(this::class.simpleName.orEmpty())
    }
}

