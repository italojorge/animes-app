package br.com.animes.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.animes.core.extensions.loadImageFromUrl
import br.com.animes.feature.home.R
import br.com.animes.feature.home.databinding.ItemAnimeBinding
import br.com.animes.feature.home.domain.model.Anime

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

    suspend fun resetList() {
        submitData(PagingData.empty())
    }

    inner class AnimeListViewHolder(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Anime, onAnimeClick: OnAnimeClick) {
            itemView.context.let { context ->
                with(binding) {
                    itemAnimeTitleTextView.text = item.title
                    itemAnimeDatePeriodTextView.apply {
                        text = item.getFormattedDate()
                        isVisible = text.isNotBlank()
                    }
                    itemAnimeTypeAndEpisodesTextView.text = if (item.episodes != null) context.getString(
                        R.string.item_home_type_and_episodes_format, item.type, item.episodes.toString()
                    ) else item.type
                    itemAnimeScoreTextView.text = item.score.toString()
                    itemAnimeImageView.loadImageFromUrl(item.imageUrl)
                }

                itemView.setOnClickListener {
                    onAnimeClick.invoke(item)
                }
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
