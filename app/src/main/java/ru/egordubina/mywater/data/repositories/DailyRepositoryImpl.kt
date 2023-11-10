package ru.egordubina.mywater.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.egordubina.mywater.domain.repositories.DailyRepository
import javax.inject.Inject

class DailyRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DailyRepository {
    override fun getDailyData(): Flow<Int> = dataStore.data.map {
        it[intPreferencesKey("WATER DAILY")] ?: 20000
    }

    override fun getDailyCurrentData(): Flow<Int> = dataStore.data.map {
        it[intPreferencesKey("WATER DAILY CURRENT")] ?: 0
    }

    override fun getGlassVolume(): Flow<Int> = dataStore.data.map {
        it[intPreferencesKey("GLASS VOLUME")] ?: 250
    }

    override suspend fun updateDailyCurrentData(data: Int) {
        dataStore.edit {
            it[intPreferencesKey("WATER DAILY CURRENT")] = data
        }
    }

    override suspend fun updateGlassVolume(glassVolume: Int) {
        dataStore.edit {
            it[intPreferencesKey("GLASS VOLUME")] = glassVolume
        }
    }
}