package net.jgarcia.populartvshows.data

import androidx.paging.PagingSource
import net.jgarcia.populartvshows.api.ShowsApi
import net.jgarcia.populartvshows.api.ShowsApi.Companion.API_KEY
import net.jgarcia.populartvshows.api.ShowsApi.Companion.LANGUAGE
import retrofit2.HttpException
import java.io.IOException

private const val MOVIES_STARTING_PAGE_INDEX = 1

class MoviesPagingSource (
        private val showsApi: ShowsApi
) : PagingSource<Int, TvShow>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        val position = params.key ?: MOVIES_STARTING_PAGE_INDEX

        return try {
            val response = showsApi.getMovies(API_KEY, LANGUAGE, position)
            val movies = response.results

            LoadResult.Page(
                    data = movies,
                    prevKey = if(position == MOVIES_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if(movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }


}