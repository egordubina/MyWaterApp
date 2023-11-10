package ru.egordubina.mywater.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.egordubina.mywater.domain.repositories.DailyRepository
import javax.inject.Inject

interface DailyUseCase {
    fun getDailyData(): Flow<Int>
    fun getDailyCurrentData(): Flow<Int>
    fun getGlassVolume(): Flow<Int>
    suspend fun updateDailyCurrentData(data: Int)
    suspend fun updateGlassVolume(glassVolume: Int)
}


class DailyUseCaseImpl @Inject constructor(
    private val dailyRepository: DailyRepository
) : DailyUseCase {
    override fun getDailyData(): Flow<Int> = dailyRepository.getDailyData()
    override fun getDailyCurrentData(): Flow<Int> = dailyRepository.getDailyCurrentData()
    override fun getGlassVolume(): Flow<Int> = dailyRepository.getGlassVolume()

    override suspend fun updateDailyCurrentData(data: Int) {
        dailyRepository.updateDailyCurrentData(data)
    }

    override suspend fun updateGlassVolume(glassVolume: Int) {
        dailyRepository.updateGlassVolume(glassVolume = glassVolume)
    }
}