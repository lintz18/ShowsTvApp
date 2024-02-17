package net.jgarcia.populartvshows.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import net.jgarcia.populartvshows.R
import net.jgarcia.populartvshows.databinding.FragmentDetailsBinding

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val tvShow = args.tvShow



            Glide.with(this@DetailsFragment)
                    .load("https://api.themoviedb.org/" + tvShow.poster_path) //Try to obtain url....
                    .error(R.drawable.ic_error)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                        ): Boolean {
                            progressBar.isVisible = false
                            textViewOriginalName.isVisible = true
                            return false
                        }

                        override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                        ): Boolean {
                            progressBar.isVisible = false
                            textViewOriginalName.isVisible = true
                            return false
                        }
                    })
                    .into(imageView)


            textViewOriginalName.text = tvShow.original_name
            Log.i("DETAILS", "Movie original name: " + tvShow.original_name)
            textViewFirstLaunch.text = tvShow.first_air_date
            textViewDescription.text = tvShow.overview
            textViewPopularity.text = tvShow.popularity.toString()
            textViewVotesAverage.text = tvShow.vote_average.toString()
            textViewVotesCount.text = tvShow.vote_count.toString()

        }

    }
}