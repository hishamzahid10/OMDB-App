package com.hisham.movie.ui.ui.home.movie.feed

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.hisham.movie.BR
import com.hisham.movie.R
import com.hisham.movie.base.BaseFragment
import com.hisham.movie.databinding.MovieDetailFragementBinding
import com.hisham.movie.pojo.movie.MovieDetailResponse
import com.hisham.movie.utils.Constants
import com.hisham.movie.utils.bindings.BindingUtils
import kotlinx.android.synthetic.main.movie_detail_fragement.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailFragment : BaseFragment<MovieDetailFragementBinding, MovieViewModel>() {


    override fun getLayoutId(): Int = R.layout.movie_detail_fragement
    override fun getViewModel(): MovieViewModel = movieViewModel
    private val movieViewModel: MovieViewModel by viewModel()

    lateinit var movieId: String
    lateinit var mView: View
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        movieViewModel.getMovieDetail(movieId)
        setUpObservers()
        initView()
        handleBackPress()
    }

    private fun getIntentData() {
        movieId = arguments?.getString(Constants.MOVIE_ID)!!
    }
    private fun setUpObservers() {
        movieViewModel.uiStateDetail.observe(viewLifecycleOwner, Observer {
            val dataState = it ?: return@Observer
            progressBar.visibility =
                if (dataState.showProgress) View.VISIBLE else View.GONE
            if (dataState.data != null && !dataState.data.consumed)
                dataState.data.consume()?.let { data ->
                    setData(data)
                }
            if (dataState.error != null && !dataState.error.consumed)
                dataState.error.consume()?.let { errorMessage ->
                    //  handle error
                    Log.d("ERROR_", errorMessage.toString())
                }
        })
    }
    private fun initView() {
        back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun handleBackPress()
    {
       mView.setFocusableInTouchMode(true)
        mView.requestFocus()
        mView.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                parentFragmentManager.popBackStack()
                true
            } else false
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setData(movieDetail: MovieDetailResponse) {
            BindingUtils.setImageSrc(mainImage, movieDetail.Poster)
        movieTitle.text = movieDetail.Title
        author.text = "Writer: ${movieDetail.Writer}"
        dateFeedDetail.text = "Actors: ${movieDetail.Actors}"
        ratings.text = "Rating: ${movieDetail.imdbRating}"
        tvDetail.text = movieDetail.Plot
    }


    companion object {
        val TAG = MovieDetailFragment::class.java.simpleName

        fun start(activity: FragmentActivity, containerId: Int, movieId: String) {
            val fragment = MovieDetailFragment()
            val bundle = Bundle().apply {
                putString(Constants.MOVIE_ID, movieId)
            }
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .addToBackStack(TAG)
                .add(containerId, fragment)
                .commit()
        }
    }
}