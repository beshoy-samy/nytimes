package com.beshoy.nytimes.features.home

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.beshoy.nytimes.R
import com.beshoy.nytimes.base.BaseActivity
import com.beshoy.nytimes.databinding.ActivityHomeBinding
import com.beshoy.nytimes.features.details.DetailsActivity
import com.beshoy.nytimes.features.home.presentation.HomeViewModel
import com.beshoy.nytimes.remote.error.ApiErrorModel
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
            DetailsActivity.start(this, article)
        }
    }

    private fun observeLiveData() {
        viewModel.articles.observe(this, {
            it?.let { articles ->
                binding.articlesWidget.submitArticles(articles)
            }
        })

        viewModel.showError.observe(this, {
            it?.let { error -> showError(error) }
        })

        viewModel.showProgress.observe(this, {
            it?.let { showProgress ->
                binding.swipeRefresh.isRefreshing = showProgress
            }
        })
    }

    private fun showError(error: ApiErrorModel) {
        Snackbar.make(binding.root, error.getErrorMessage(this), Snackbar.LENGTH_INDEFINITE)
            .also { snackbar ->
                snackbar.setAction(R.string.retry) {
                    snackbar.dismiss()
                    viewModel.getMostPopularArticles()
                }
            }
            .show()
    }

    override fun getLayoutResId(): Int = R.layout.activity_home
}