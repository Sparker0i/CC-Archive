package me.sparker0i.currencyconverter.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
data class Rates(@NonNull @PrimaryKey var currency: String, @NonNull var value: Double)