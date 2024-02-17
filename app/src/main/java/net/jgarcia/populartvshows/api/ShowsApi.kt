package net.jgarcia.populartvshows.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ShowsApi {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "c6aeee577586ba38e487b74dfede5deb"//BuildConfig.MOVIES_ACCESS_KEY
        const val LANGUAGE = "en-US"
    }

    @GET("tv/popular")
    suspend fun getMovies(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TvShowsResponse

//    @GET("tv/{tv_id}")
//    suspend fun getMovieImage(
//        @Path("tv_id") id: Int,
//        @Query("api_key") key: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): Movie

//    @GET("authentication/token/new")
//    suspend fun getToken(
//        @Query("api_key") key: String
//    ): UserToken
}