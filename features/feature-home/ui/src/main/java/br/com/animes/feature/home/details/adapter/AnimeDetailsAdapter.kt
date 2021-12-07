package br.com.animes.feature.home.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.animes.feature.home.databinding.ItemAnimeDetailsBinding

class AnimeDetailsAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<AnimeDetailsAdapter.AnimeDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeDetailsViewHolder {
        return AnimeDetailsViewHolder(ItemAnimeDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AnimeDetailsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class AnimeDetailsViewHolder(private val binding: ItemAnimeDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.itemAnimeDetailsTextView.text = item
        }
    }

    override fun getItemCount() = items.size
}
