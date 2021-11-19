package br.com.animes.core.bases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : BaseViewHolder<T>> : RecyclerView.Adapter<V>() {
    private var itemClickListener: OnAdapterItemClickListener<T> = {}

    var items: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun createViewHolderInstance(view: View): V

    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = createViewHolderInstance(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: V, position: Int) {
        val item = items[position]

        holder.itemView.setOnClickListener {
            itemClickListener(item)
        }

        holder.bind(item)
    }

    fun setOnItemClickListener(listener: OnAdapterItemClickListener<T>) {
        itemClickListener = listener
    }

}

abstract class BaseViewHolder<T>(v: View) : RecyclerView.ViewHolder(v) {
    abstract fun bind(item: T)
}

typealias OnAdapterItemClickListener<T> = (T) -> Unit
