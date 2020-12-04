package com.beshoy.nytimes.features.home

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.beshoy.nytimes.R
import com.beshoy.nytimes.base.BaseActivity
import com.beshoy.nytimes.databinding.ActivityHomeBinding
import com.beshoy.nytimes.features.home.presentation.HomeViewModel
import com.beshoy.nytimes.remote.error.getErrorMessage
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()

    override fun bindViews(savedInstanceState: Bundle?) {
        setupSwipeRefresh()
        setupArticlesList()
        observeLiveData()
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(this, R.color.progress_color)
        )
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMostPopularArticles()
        }
    }

    private fun setupArticlesList() {
        binding.articlesWidget.articleClickListener = { article, position ->
            Timber.i("article clicked $article")
        }
    }

    private fun observeLiveData() {
        viewModel.articles.observe(this, {
            it?.let { articles ->
                binding.articlesWidget.submitArticles(articles)
            }
        })

        viewModel.showError.observe(this, {
            it?.let { error ->
                Snackbar.make(binding.root, error.getErrorMessage(this), Snackbar.LENGTH_LONG)
                    .show()
            }
        })

        viewModel.showProgress.observe(this, {
            it?.let { showProgress ->
                binding.swipeRefresh.isRefreshing = showProgress
            }
        })
    }

    override fun getLayoutResId(): Int = R.layout.activity_home
}