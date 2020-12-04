package com.beshoy.nytimes.features

import android.os.Bundle
import com.beshoy.nytimes.R
import com.beshoy.nytimes.base.BaseActivity
import com.beshoy.nytimes.databinding.ActivityHomeBinding
import com.beshoy.nytimes.features.presentation.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()

    override fun bindViews(savedInstanceState: Bundle?) {

    }

    override fun getLayoutResId(): Int = R.layout.activity_home
}