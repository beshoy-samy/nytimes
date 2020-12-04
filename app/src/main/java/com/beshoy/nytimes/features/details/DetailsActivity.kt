package com.beshoy.nytimes.features.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.beshoy.nytimes.R
import com.beshoy.nytimes.base.BaseActivity
import com.beshoy.nytimes.databinding.ActivityDetailsBinding
import com.beshoy.nytimes.models.NyTimesArticleModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {

    override val viewModel: DetailsViewModel by viewModel()

    private lateinit var article: NyTimesArticleModel

    override fun bindViews(savedInstanceState: Bundle?) {
        article = requireNotNull(intent?.getParcelableExtra(ARTICLE_KEY))
    }

    override fun getLayoutResId(): Int = R.layout.activity_details

    companion object {

        private const val ARTICLE_KEY = "ARTICLE_KEY"

        fun start(context: Context, article: NyTimesArticleModel) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(ARTICLE_KEY, article)
            context.startActivity(intent)
        }
    }
}