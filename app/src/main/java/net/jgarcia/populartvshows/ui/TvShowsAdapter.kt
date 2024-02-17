package net.jgarcia.populartvshows.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.jgarcia.populartvshows.data.TvShow
import net.jgarcia.populartvshows.databinding.ItemMovieBinding

class TvShowsAdapter (private val listener: OnItemClickListener) :
        PagingDataAdapter<TvShow, TvShowsAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
            RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition //Necesitamos la posicion
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position) // Recuperamos el objecto por la posición
                    if (item != null) {
                        listener.OnItemClick(item)
                    }
                }
            }
        }

        fun bind(tvShow: TvShow) {
            binding.apply {
//                Glide.with(itemView)
//                        .load(movie.urls.regular)
//                        .centerCrop()
//                        .transition(DrawableTransitionOptions.withCrossFade())
//                        .error(R.drawable.ic_error)
//                        .into(imageView)

                textViewMovie.text = tvShow.name
            }
        }
    }

    interface OnItemClickListener {
        fun OnItemClick(tvShow: TvShow)
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow) =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow) =
                    oldItem == newItem
        }
    }

}