package me.sparker0i.currencyconverter.model

import java.util.*

data class CurrencyData(var date: Date , var rates: Map<String , Double>)