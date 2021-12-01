package br.com.animes.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.main.databinding.ItemAnimeBinding

class AnimeListAdapter(
    private val onAnimeClick: OnAnimeClick
) : PagingDataAdapter<Anime, AnimeListAdapter.AnimeListViewHolder>(ANIME_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListViewHolder {
        return AnimeListViewHolder(ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AnimeListViewHolder, position: Int) {
        val animeItem = getItem(position)
        animeItem?.let {
            holder.bind(animeItem, onAnimeClick)
        }
    }


    inner class AnimeListViewHolder(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Anime, onAnimeClick: OnAnimeClick) {
            with(binding) {

            }
        }
    }

    companion object {
        private val ANIME_COMPARATOR = object : DiffUtil.ItemCallback<Anime>() {
            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean =
                oldItem == newItem
        }
    }
}

typealias OnAnimeClick = (Anime) -> Unit
