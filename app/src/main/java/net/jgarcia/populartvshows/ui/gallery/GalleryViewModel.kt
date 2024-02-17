package net.jgarcia.populartvshows.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import net.jgarcia.populartvshows.data.TvShowRepository

class GalleryViewModel @ViewModelInject constructor(
        private val repository: TvShowRepository,
        @Assisted state: SavedStateHandle
): ViewModel() {

    val movies = repository.getResults().cachedIn(viewModelScope)

}