package br.com.animes.feature.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import br.com.animes.core.bases.BaseFragment
import br.com.animes.core.utils.viewbinding.viewBinding
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.home.adapter.AnimeListAdapter
import br.com.animes.feature.main.databinding.FragmentHomeBinding
import br.com.animes.feature.home.model.FilterTopAnimesEnum
import com.google.android.material.chip.Chip
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private var binding: FragmentHomeBinding by viewBinding()

    //    private val navigation: AuthNavigation by navDirections()
    private val viewModel: HomeViewModel by viewModel()
    private var searchJob: Job? = null
    private val adapter by lazy { AnimeListAdapter(::onAnimeClick) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillChipGroup()
        addObservers(viewLifecycleOwner)
    }

    private fun onAnimeClick(anime: Anime) {

    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getAnimesByQuery(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun fillChipGroup() {
        FilterTopAnimesEnum.values().forEach {
            val chip = Chip(requireContext())
            chip.text = it.name.lowercase()
            binding.homeChipGroup.addView(chip)
        }
    }

    private fun addObservers(owner: LifecycleOwner) {
//        viewModel.loginViewState.observe(owner) { viewState ->
//            viewState.handleIt(
//                onSuccess = {
//                    Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_LONG).show()
//                },
//                onFailure = { showErrorAlert(it) },
//                isLoading = {
//                    binding.loginLoadingButton.isLoading = it
//                }
//            )
//        }
    }
}
