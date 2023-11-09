package ru.egordubina.mywater.domain.repositories

import kotlinx.coroutines.flow.Flow

interface DailyWaterRepository {
    fun getDailyWaterValue(): Flow<Int>
}
