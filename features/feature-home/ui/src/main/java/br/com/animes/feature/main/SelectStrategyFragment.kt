package br.com.animes.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.animes.base.feature.core.BaseFragment
import br.com.animes.base.feature.utils.navigation.navDirections
import br.com.animes.base.feature.utils.viewbinding.viewBinding
import br.com.animes.feature.main.databinding.FragmentSelectStrategyBinding

class SelectStrategyFragment : BaseFragment() {
    private val navigation: MainNavigation by navDirections()
    private var binding: FragmentSelectStrategyBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectStrategyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.selectStrategyBC100ImageButton.setToBlackAndWhite()
//        binding.selectStrategyAlavancagemImageButton.setToBlackAndWhite()
    }
}

