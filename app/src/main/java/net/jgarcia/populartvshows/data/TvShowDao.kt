package net.jgarcia.populartvshows.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShow)

    @Update
    suspend fun update(tvShow: TvShow)

    @Delete
    suspend fun delete(tvShow: TvShow)


}