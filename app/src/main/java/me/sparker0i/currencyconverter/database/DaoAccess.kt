package me.sparker0i.currencyconverter.database

import android.arch.persistence.room.*
import me.sparker0i.currencyconverter.model.Rates

@Dao
interface DaoAccess {
    @Insert fun insertSingleCurrency(rate: Rates)
    @Insert fun insertMultipleCurrencies(rates: List<Rates>)
    @Query("SELECT * FROM Rates WHERE currency=:currency") fun fetchOneCurrency(currency: String)
    @Query("SELECT COUNT(*) FROM Rates") fun fetchTableSize(): Int
    @Update fun updateCurrency(rate: Rates)
    @Delete fun deleteCurrency(rate: Rates)
}