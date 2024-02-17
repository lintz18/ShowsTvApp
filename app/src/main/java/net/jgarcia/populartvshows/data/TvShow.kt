package net.jgarcia.populartvshows.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tv_show_table")
@Parcelize
data class TvShow(
    val backdrop_path: String,
    val first_air_date: String,
    @PrimaryKey val id: Int,
    val name: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Double
//    @PrimaryKey(autoGenerate = true) val id: Int = 0
): Parcelable




//"page": 1,
//"results": [
//{
//    "backdrop_path": "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
//    "first_air_date": "2021-03-19",
//    "genre_ids": [
//    10765,
//    10759,
//    18,
//    10768
//    ],
//    "id": 88396,
//    "name": "The Falcon and the Winter Soldier",
//    "origin_country": [
//    "US"
//    ],
//    "original_language": "en",
//    "original_name": "The Falcon and the Winter Soldier",
//    "overview": "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
//    "popularity": 5146.165,
//    "poster_path": "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
//    "vote_average": 7.9,
//    "vote_count": 4605
//},