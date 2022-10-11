package com.hisham.movie.ui.ui.home.movie.feed

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hisham.movie.BR
import com.hisham.movie.R
import com.hisham.movie.base.BaseFragment
import com.hisham.movie.databinding.MovieFragmentBinding
import com.hisham.movie.pojo.movie.Search
import com.hisham.movie.utils.OnItemClick
import kotlinx.android.synthetic.main.movie_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList


class MovieFragment : BaseFragment<MovieFragmentBinding, MovieViewModel>(), OnItemClick {

    override fun getLayoutId(): Int = R.layout.movie_fragment
    override fun getViewModel(): MovieViewModel = movieViewModel
    private val movieViewModel: MovieViewModel by viewModel()

    private var itemAdapter: MovieItemAdapter? = null

    private var movieList: ArrayList<Search>? = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUpObservers()
        getMovieLocalData()
        onBackPress()
        handleRefresh()
    }

    private fun getMovieLocalData()
    {
        context?.let { movieViewModel.getMovieList(it) }?.observe(viewLifecycleOwner, Observer {

            if (it == null || it.isEmpty()) {
                movieViewModel.getAllMovies()
            }
            else {
                    movieList = ArrayList(it)
                    rvMovie.visibility = View.VISIBLE
                    no_Results.visibility = View.GONE
                    setUpMovieRecycler()
            }
        })
    }

    private fun handleRefresh()
    {
        swipeRefresh.setOnRefreshListener {
            movieViewModel.getAllMovies()
            swipeRefresh.isRefreshing = false
        }
    }


    private fun initViews() {
        linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rvMovie.layoutManager = linearLayoutManager
    }
    private fun setUpObservers() {
        movieViewModel.uiState.observe(viewLifecycleOwner, Observer {
            val dataState = it ?: return@Observer
            progressBar.visibility =
                if (dataState.showProgress) View.VISIBLE else View.GONE
            if (dataState.data != null && !dataState.data.consumed)
                dataState.data.consume()?.let { data ->
                    movieList = data.Search

                    if (movieList?.size == 0) {
                        no_Results.visibility = View.VISIBLE
                        rvMovie.visibility = View.GONE
                    } else {
                        rvMovie.visibility = View.VISIBLE
                        no_Results.visibility = View.GONE
                        setUpMovieRecycler()
                    }

                    movieList?.let { it1 -> movieViewModel.insertData(requireContext(), it1) }
                }
            if (dataState.error != null && !dataState.error.consumed)
                dataState.error.consume()?.let { errorMessage ->
                    //  handle error
                    Log.d("ERROR_", errorMessage.toString())
                }
        })
    }
    private fun setUpMovieRecycler() {

        if (itemAdapter == null)
            itemAdapter = context?.let { movieList?.let { it1 -> MovieItemAdapter(it, it1,this) } }
        rvMovie?.adapter = itemAdapter
    }

    companion object {
        val TAG = MovieFragment::class.java.simpleName

        fun start(activity: FragmentActivity, containerId: Int) {
            val fragment = MovieFragment()
            activity.supportFragmentManager.beginTransaction()
                .add(containerId, fragment)
                .addToBackStack(TAG)
                .commit()
        }
    }

    private fun onBackPress()
    {
        view?.isFocusableInTouchMode = true
        view?.requestFocus()

        view?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.getAction() === KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        requireActivity().finish()
                        return true
                    }
                }
                return false
            }
        })
    }
    override fun onItemClick(any: Any) {
        val itemDetail = any as Search
        itemDetail.imdbID?.let {
            MovieDetailFragment.start(requireActivity(), R.id.main_screen_container,
                it
            )
        }
    }
}