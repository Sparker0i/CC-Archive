package me.sparker0i.currencyconverter.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import me.sparker0i.currencyconverter.activity.MainActivity
import me.sparker0i.currencyconverter.model.Rates

@Database(entities = arrayOf(Rates::class) , version = 1)
abstract class RateDatabase : RoomDatabase() {
    abstract fun daoAccess() : DaoAccess

    companion object {
        private var INSTANCE: RateDatabase? = null
        private const val DATABASE_NAME = "rates_db"

        fun getInstance(context: Context): RateDatabase? {
            if (INSTANCE == null) {
                synchronized(RateDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context , RateDatabase::class.java , this.DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}