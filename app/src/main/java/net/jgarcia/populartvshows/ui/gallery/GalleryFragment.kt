package net.jgarcia.populartvshows.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import net.jgarcia.populartvshows.R
import net.jgarcia.populartvshows.data.TvShow
import net.jgarcia.populartvshows.databinding.FragmentGalleryBinding
import net.jgarcia.populartvshows.ui.TvShowsAdapter


@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery), TvShowsAdapter.OnItemClickListener {

    private val viewModel by viewModels<GalleryViewModel>()

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGalleryBinding.bind(view)

        val adapter = TvShowsAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null //
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = TvShowLoadStateAdapter {
                        adapter.retry()
                    },
                    footer = TvShowLoadStateAdapter {
                        adapter.retry()
                    },
            )
            buttonRetry.setOnClickListener{
                adapter.retry()
            }
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                //Si los datos en la vista está vacía
                if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }

        setHasOptionsMenu(true) //activa el menu
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Interface Adapter
    override fun OnItemClick(tvShow: TvShow) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(tvShow)
        findNavController().navigate(action)
    }

}