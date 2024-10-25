package com.ratnavidyakanawade.mphasisandroidcodingproject

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ratnavidyakanawade.mphasisandroidcodingproject.DataStoreManager.PreferencesKeys.LAST_SEARCHED_CITY
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "settings")

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferencesKeys {
        val LAST_SEARCHED_CITY = stringPreferencesKey("last_searched_city")
    }
    // Save the last searched city
    suspend fun saveCity(city: String) {
        context.dataStore.edit { preferences ->
            preferences[LAST_SEARCHED_CITY] = city
        }
    }

    // Get the last searched city
    val lastSearchedCity: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[LAST_SEARCHED_CITY]
        }
}