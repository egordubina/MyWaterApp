package ru.egordubina.mywater.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.egordubina.mywater.domain.repositories.DailyRepository
import javax.inject.Inject

interface DailyUseCase {
    fun getDailyData(): Flow<Int>
    fun getDailyCurrentData(): Flow<Int>
    suspend fun updateDailyCurrentData(data: Int)
}


class DailyUseCaseImpl @Inject constructor(
    private val dailyRepository: DailyRepository
) : DailyUseCase {
    override fun getDailyData(): Flow<Int> = dailyRepository.getDailyData()
    override fun getDailyCurrentData(): Flow<Int> = dailyRepository.getDailyCurrentData()
    override suspend fun updateDailyCurrentData(data: Int) {
        dailyRepository.updateDailyCurrentData(data)
    }
}