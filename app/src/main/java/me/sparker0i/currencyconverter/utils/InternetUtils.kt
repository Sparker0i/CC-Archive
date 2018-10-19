package me.sparker0i.currencyconverter.utils

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import me.sparker0i.currencyconverter.activity.MainActivity
import me.sparker0i.currencyconverter.model.CurrencyData
import me.sparker0i.currencyconverter.model.Rates
import org.json.JSONObject

class InternetUtils {
    companion object {
        fun updateDatabase() {
            val results = getResponse()
            val currencies = ArrayList<Rates>()
            for (result in results!!) {
                currencies.add(Rates(result.key , result.value))
            }
            MainActivity.rateDatabase?.daoAccess()?.insertMultipleCurrencies(currencies)
        }

        private fun getResponse(): Map<String , Double>? {
            var results : Map<String , Double>? = null
            AndroidNetworking.get("https://api.exchangeratesapi.io/latest")
                    .build()
                    .getAsObject(CurrencyData::class.java , object: ParsedRequestListener<CurrencyData> {
                        override fun onResponse(response: CurrencyData?) {
                            results = response?.rates
                            println(results)
                        }

                        override fun onError(anError: ANError?) {

                        }
                    })
            return results
        }
    }
}