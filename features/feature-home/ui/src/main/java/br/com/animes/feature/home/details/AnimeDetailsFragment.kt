package br.com.animes.feature.home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.animes.core.bases.BaseFragment
import br.com.animes.core.utils.viewbinding.viewBinding
import br.com.animes.feature.home.databinding.FragmentAnimeDetailsBinding

class AnimeDetailsFragment : BaseFragment() {
    private var binding: FragmentAnimeDetailsBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
