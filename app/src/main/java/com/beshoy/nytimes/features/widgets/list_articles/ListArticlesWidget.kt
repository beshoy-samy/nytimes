package com.beshoy.nytimes.features.widgets.list_articles

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beshoy.nytimes.R
import com.beshoy.nytimes.models.NyTimesArticleModel
import com.beshoy.nytimes.utils.setDivider

class ListArticlesWidget(context: Context, attributes: AttributeSet) :
    RecyclerView(context, attributes) {

    var articleClickListener: ((article: NyTimesArticleModel, position: Int) -> Unit)? = null

    private val articlesAdapter = ArticlesAdapter { model, position ->
        articleClickListener?.invoke(model, position)
    }

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = articlesAdapter
        clipToPadding = false
        setDivider(R.drawable.list_divider, DividerItemDecoration.VERTICAL)
    }

    fun submitArticles(articles: List<NyTimesArticleModel>) =
        articlesAdapter.submitList(articles)
}