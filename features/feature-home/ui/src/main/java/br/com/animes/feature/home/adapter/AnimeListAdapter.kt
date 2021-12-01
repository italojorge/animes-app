package br.com.animes.feature.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.animes.core.extensions.loadImageFromUrl
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.main.R
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
            itemView.context.let { context ->
                with(binding) {
                    itemAnimeTitleTextView.text = item.title
                    itemAnimeDatePeriodTextView.text = setupAnimeDatePeriodTextView(item, context)
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

        private fun ItemAnimeBinding.setupAnimeDatePeriodTextView(
            item: Anime,
            context: Context
        ) = when {
            item.startDate != null && item.endDate != null -> {
                context.getString(
                    R.string
                        .item_home_date_period_format, item.startDate, item.endDate
                )
            }
            item.startDate != null -> {
                item.startDate
            }
            item.endDate != null -> {
                item.endDate
            }
            else -> {
                itemAnimeDatePeriodTextView.isVisible = false
                ""
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
