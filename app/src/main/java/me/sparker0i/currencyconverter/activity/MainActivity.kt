package me.sparker0i.currencyconverter.activity

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.jacksonandroidnetworking.JacksonParserFactory
import me.sparker0i.currencyconverter.R
import me.sparker0i.currencyconverter.database.RateDatabase
import me.sparker0i.currencyconverter.utils.InternetUtils

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic private val DATABASE_NAME = "rates_db"
        @JvmStatic var rateDatabase: RateDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        networkInitialize()
        databaseInitialize()
        Thread {
            Runnable {
                if (rateDatabase?.daoAccess()?.fetchTableSize() == 0) {
                    InternetUtils.updateDatabase()
                }
        }}.start()
    }

    private fun networkInitialize() {
        AndroidNetworking.initialize(this)
        AndroidNetworking.setParserFactory(JacksonParserFactory())
    }

    private fun databaseInitialize() {
        rateDatabase = Room.databaseBuilder(this , RateDatabase::class.java , DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
