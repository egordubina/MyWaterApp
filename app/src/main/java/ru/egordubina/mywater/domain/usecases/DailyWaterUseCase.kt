package ru.egordubina.mywater.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.egordubina.mywater.domain.repositories.DailyWaterRepository
import javax.inject.Inject

interface DailyWaterUseCase {
    fun getDailyWaterValue(): Flow<Int>
}


class DailyWaterUseCaseImpl @Inject constructor(
    private val dailyWaterRepository: DailyWaterRepository
) : DailyWaterUseCase {
    override fun getDailyWaterValue(): Flow<Int> = dailyWaterRepository.getDailyWaterValue()
}