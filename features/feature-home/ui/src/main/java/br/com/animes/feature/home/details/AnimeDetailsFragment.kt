package br.com.animes.feature.home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.animes.core.bases.BaseFragment
import br.com.animes.core.dialog.AlertDialogExtension.showErrorAlert
import br.com.animes.core.extensions.loadImageFromUrl
import br.com.animes.core.extensions.setupToolbar
import br.com.animes.core.utils.viewbinding.viewBinding
import br.com.animes.feature.home.R
import br.com.animes.feature.home.databinding.FragmentAnimeDetailsBinding
import br.com.animes.feature.home.details.adapter.AnimeDetailsAdapter
import br.com.animes.feature.home.domain.model.AnimeDetails
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimeDetailsFragment : BaseFragment() {
    private var binding: FragmentAnimeDetailsBinding by viewBinding()
    private val args: AnimeDetailsFragmentArgs by navArgs()
    private val viewModel: AnimeDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(binding.animeDetailsToolbar)
        addObservers(viewLifecycleOwner)
        viewModel.getAnimesDetails(args.id)
    }

    private fun addObservers(owner: LifecycleOwner) {
        viewModel.viewState.observe(owner) { viewState ->
            viewState.handleIt(
                onSuccess = {
                    handleSuccessState(it)
                },
                onFailure = {
                    showErrorAlert(it) {
                        findNavController().navigateUp()
                    }
                },
                isLoading = {
                    binding.animeDetailsLoadingFrameLayout.isVisible = it
                }
            )
        }
    }

    private fun handleSuccessState(it: AnimeDetails) {
        val informationList = AnimeInformationList.getList(requireContext(), it)
        binding.apply {
            animeDetailsRecyclerView.adapter = AnimeDetailsAdapter(informationList)
            animeDetailsTitleTextView.text = it.title
            animeDetailsSynopsisTextView.text = it.synopsis
            animeDetailsImageView.loadImageFromUrl(it.imageUrl)
            animeDetailsScoreTextView.text = getString(R.string.anime_details_score_label, it.score.toString())
        }
    }
}
