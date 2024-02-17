package net.jgarcia.populartvshows.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import net.jgarcia.populartvshows.di.AppModule
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [TvShow::class], version = 1)
abstract class TvShowDatabase: RoomDatabase() {

    abstract fun taskDao(): TvShowDao

    class Callback @Inject constructor(
        private val database: Provider<TvShowDatabase>,
        @AppModule.ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            //Operaciones DB
            val dao = database.get().taskDao()

        }
    }
}