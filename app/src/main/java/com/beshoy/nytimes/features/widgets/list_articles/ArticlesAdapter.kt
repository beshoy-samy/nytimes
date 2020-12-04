package com.beshoy.nytimes.features.widgets.list_articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beshoy.nytimes.databinding.ItemListArticleBinding
import com.beshoy.nytimes.models.NyTimesArticleModel

class ArticlesAdapter(private val listener: (model: NyTimesArticleModel, position: Int) -> Unit) :
    ListAdapter<NyTimesArticleModel, ArticlesAdapter.ViewHolder>(
        ArticlesDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), ArticlesItemClickListener(listener))
    }

    class ViewHolder private constructor(
        private val binding: ItemListArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: NyTimesArticleModel, listener: ArticlesItemClickListener) {
            binding.model = model
            binding.position = layoutPosition
            binding.clickListener = listener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListArticleBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ArticlesItemClickListener(
    private val listener: (model: NyTimesArticleModel, position: Int) -> Unit
) {
    fun merchantOfferItemClicked(model: NyTimesArticleModel, position: Int) =
        listener.invoke(model, position)
}

class ArticlesDiffCallback : DiffUtil.ItemCallback<NyTimesArticleModel>() {

    override fun areItemsTheSame(old: NyTimesArticleModel, aNew: NyTimesArticleModel): Boolean {
        return old.id == aNew.id
    }

    override fun areContentsTheSame(old: NyTimesArticleModel, aNew: NyTimesArticleModel): Boolean {
        return old == aNew
    }

}