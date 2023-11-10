package ru.egordubina.mywater.domain.repositories

import kotlinx.coroutines.flow.Flow

interface DailyRepository {
    fun getDailyData(): Flow<Int>
    fun getDailyCurrentData(): Flow<Int>
    fun getGlassVolume(): Flow<Int>
    suspend fun updateDailyCurrentData(data: Int)
    suspend fun updateGlassVolume(glassVolume: Int)
}
