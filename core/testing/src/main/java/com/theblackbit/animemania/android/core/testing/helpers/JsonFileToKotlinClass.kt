package com.theblackbit.animemania.android.core.testing.helpers

import android.content.Context
import com.google.gson.Gson

inline fun <reified T : Any> jsonFileToKotlinClass(context: Context, fileName: String): T {
    return Gson().fromJson(
        context.assets.open(fileName).bufferedReader().use { it.readText() },
        T::class.java,
    )
}
