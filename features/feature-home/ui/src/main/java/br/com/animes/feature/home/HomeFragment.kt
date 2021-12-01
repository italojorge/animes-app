package br.com.animes.feature.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import br.com.animes.core.bases.BaseFragment
import br.com.animes.core.extensions.getSelectedChipText
import br.com.animes.core.extensions.scrollToTop
import br.com.animes.core.utils.viewbinding.viewBinding
import br.com.animes.feature.home.adapter.AnimeListAdapter
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.home.domain.model.FilterTopAnimesEnum
import br.com.animes.feature.main.databinding.FragmentHomeBinding
import br.com.animes.feature.main.databinding.SingleChipLayoutBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private var binding: FragmentHomeBinding by viewBinding()

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
        setupRecyclerView()
        fillChipGroup()
        onChipClick()
        filterAnimeList()
    }

    private fun setupRecyclerView() {
        binding.homeRecyclerView.adapter = adapter

        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect {
                    binding.homeRecyclerView.scrollToTop()
                }
        }
    }

    private fun onChipClick() {
        binding.homeChipGroup.setOnCheckedChangeListener { _, _ ->
            filterAnimeList()
        }
    }

    private fun onAnimeClick(anime: Anime) {
        Toast.makeText(requireContext(), "Anime Clicado: ${anime.title}", Toast.LENGTH_SHORT).show()
    }

    private fun filterAnimeList() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getAnimesByFilter(binding.homeChipGroup.getSelectedChipText()).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun fillChipGroup() {
        FilterTopAnimesEnum.values().forEachIndexed { index, enum ->
            val chip: Chip = SingleChipLayoutBinding.inflate(
                LayoutInflater.from(context),
                binding.homeChipGroup, false
            ).root
            chip.text = enum.name.lowercase()
            if (index == FIRST_CHIP_INDEX) chip.isChecked = true
            binding.homeChipGroup.addView(chip)
        }
    }

    companion object {
        private const val FIRST_CHIP_INDEX = 0
    }
}
