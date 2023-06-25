package com.example.swapiapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.swapiapp.data.local.model.FilmsConverter
import com.example.swapiapp.data.local.model.PeopleEntity
import com.example.swapiapp.data.local.model.StarshipEntity

@Database(
    entities = [PeopleEntity::class, StarshipEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(FilmsConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

    abstract fun starshipsDao(): StarshipsDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /** метод возвращает текущую базу данных если она существует,
         * если нет то создает новую.
         *
         * @param context конекст приложения.
         * @return экземпляр базы данных.
         */
        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}