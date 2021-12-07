package br.com.animes.feature.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import br.com.animes.core.bases.BaseFragment
import br.com.animes.core.extensions.getCheckedChipText
import br.com.animes.core.extensions.hasChipChecked
import br.com.animes.core.extensions.hideKeyboard
import br.com.animes.core.extensions.scrollToTop
import br.com.animes.core.extensions.setupToolbar
import br.com.animes.core.utils.viewbinding.viewBinding
import br.com.animes.feature.home.adapter.AnimeListAdapter
import br.com.animes.feature.home.databinding.FragmentHomeBinding
import br.com.animes.feature.home.databinding.SingleChipLayoutBinding
import br.com.animes.feature.home.domain.model.Anime
import com.google.android.material.chip.Chip
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private var binding: FragmentHomeBinding by viewBinding()

    private val viewModel: HomeViewModel by viewModel()
    private var searchJob: Job? = null
    private val adapter by lazy { AnimeListAdapter(::onAnimeClick) }
    private lateinit var searchViewMenu: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(binding.homeToolbar)
        setupRecyclerView()
        fillChipGroup()
        onChipClick()
        filterAnimeList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchMenu: MenuItem = menu.findItem(R.id.menu_search)
        searchViewMenu = searchMenu.actionView as SearchView
        searchViewMenu.queryHint = getString(R.string.home_menu_search_view_hint)
        searchViewMenu.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchAnimeListByQuery(query.orEmpty())
                binding.homeChipGroup.clearCheck()
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }


    private fun setupRecyclerView() {
        binding.homeRecyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
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
            if (binding.homeChipGroup.hasChipChecked()) {
                searchViewMenu.setQuery("", false)
                filterAnimeList()
            }
        }
    }

    private fun onAnimeClick(anime: Anime) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToAnimeDetailsFragment(anime.id)
        )
    }

    private fun searchAnimeListByQuery(queryText: String) {
        lifecycleScope.launchWhenStarted { adapter.resetList() }
        searchJob?.cancel()
        searchJob = lifecycleScope.launchWhenStarted {
            viewModel.getAnimesByQuery(queryText).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun filterAnimeList() {
        val filter = FilterTopAnimesBindingEnum.valueOfByStringRes(
            requireContext(), binding.homeChipGroup.getCheckedChipText()
        )

        lifecycleScope.launchWhenStarted { adapter.resetList() }
        searchJob?.cancel()
        searchJob = lifecycleScope.launchWhenStarted {
            viewModel.getAnimesByFilter(filter).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun fillChipGroup() {
        FilterTopAnimesBindingEnum.values().forEachIndexed { index, enum ->
            val chip: Chip = SingleChipLayoutBinding.inflate(
                LayoutInflater.from(context),
                binding.homeChipGroup, false
            ).root
            chip.text = getString(enum.stringRes)
            if (index == FIRST_CHIP_INDEX) chip.isChecked = true
            binding.homeChipGroup.addView(chip)
        }
    }

    companion object {
        private const val FIRST_CHIP_INDEX = 0
    }
}
