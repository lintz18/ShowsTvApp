package net.jgarcia.populartvshows.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import net.jgarcia.populartvshows.api.ShowsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository@Inject constructor(private val showsApi: ShowsApi) {

    fun getResults() =
            Pager(
                    config = PagingConfig(
                            pageSize = 20,
                            maxSize = 100,
                            enablePlaceholders = false
                    ),
                    pagingSourceFactory = { MoviesPagingSource(showsApi) }
            ).liveData

}