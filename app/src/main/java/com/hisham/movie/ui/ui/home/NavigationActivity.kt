package com.hisham.movie.ui.ui.home

import android.os.Bundle
import com.hisham.movie.BR
import com.hisham.movie.R
import com.hisham.movie.base.BaseActivity
import com.hisham.movie.databinding.ActivityNavigationBinding
import com.hisham.movie.ui.ui.home.movie.feed.MovieFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
class NavigationActivity : BaseActivity<ActivityNavigationBinding, NavigationViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_navigation
    override fun getViewModel(): NavigationViewModel = navigationViewModel
    private val navigationViewModel: NavigationViewModel by viewModel()
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MovieFragment.start(this, R.id.main_screen_container)
    }

}