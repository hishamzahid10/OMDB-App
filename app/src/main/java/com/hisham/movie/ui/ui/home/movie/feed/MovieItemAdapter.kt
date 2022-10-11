package com.hisham.movie.ui.ui.home.movie.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hisham.movie.R
import com.hisham.movie.pojo.movie.Search
import com.hisham.movie.utils.OnItemClick
import com.hisham.movie.utils.bindings.BindingUtils


class MovieItemAdapter(private val context: Context, private val itemList:ArrayList<Search>, val onItemClick: OnItemClick)
    : RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           val view = mInflater.inflate(R.layout.row_movie_item, parent, false)
           return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieItemAdapter.ViewHolder, position: Int) {
        val item = itemList[position]
        if(item != null)
        {
            holder.author?.text = item.Type
            holder.rating?.text = "4.9"
            holder.ratingCount?.text = "(120 Ratings)"
            holder.title?.text = item.Title
            BindingUtils.setImageSrc(holder.moviesImage!!, item.Poster)
        }
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = null
        var author: TextView? = null
        var rating: TextView? = null
        var ratingCount: TextView? = null
        var moviesImage: ImageView? = null

        private fun registerView() {
            title = view.findViewById(R.id.title)
            author = view.findViewById(R.id.author)
            rating = view.findViewById(R.id.ratings)
            ratingCount = view.findViewById(R.id.ratings_count)
            moviesImage = view.findViewById(R.id.movie_image)
        }

        private fun registerListener() {
            view.setOnClickListener { handleClickListener() }
        }

        fun handleClickListener() {
            onItemClick.onItemClick(itemList.get(adapterPosition))

        }

        init {
            registerView()
            registerListener()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}