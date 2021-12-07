package br.com.animes.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.animes.feature.home.databinding.WidgetAnimeListLoadStateBinding

class AnimeListLoadStateAdapter(private val onClickRetry: () -> Unit) : LoadStateAdapter<AnimeListLoadStateAdapter.AnimeListLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: AnimeListLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState, onClickRetry = onClickRetry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): AnimeListLoadStateViewHolder {
        return AnimeListLoadStateViewHolder(WidgetAnimeListLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class AnimeListLoadStateViewHolder(
        private val binding: WidgetAnimeListLoadStateBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState, onClickRetry: () -> Unit) {
            binding.loadStateFooterRetryButton.setOnClickListener { onClickRetry.invoke() }
            if (loadState is LoadState.Error) {
                binding.loadStateFooterErrorMsgTextView.text = loadState.error.localizedMessage
            }
            binding.loadStateFooterProgressBar.isVisible = loadState is LoadState.Loading
            binding.loadStateFooterRetryButton.isVisible = loadState is LoadState.Error
            binding.loadStateFooterErrorMsgTextView.isVisible = loadState is LoadState.Error
        }
    }
}