package ru.egordubina.mywater.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.egordubina.mywater.domain.repositories.DailyWaterRepository
import javax.inject.Inject

class DailyWaterRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DailyWaterRepository {
    override fun getDailyWaterValue(): Flow<Int> = dataStore.data.map {
        it[intPreferencesKey("WATER DAILY")] ?: 20000
    }
}