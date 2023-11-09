package ru.egordubina.mywater.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.egordubina.mywater.domain.repositories.DailyWaterRepository
import ru.egordubina.mywater.domain.usecases.DailyWaterUseCase
import ru.egordubina.mywater.domain.usecases.DailyWaterUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideDailyWaterUseCase(dailyWaterRepository: DailyWaterRepository): DailyWaterUseCase =
        DailyWaterUseCaseImpl(dailyWaterRepository = dailyWaterRepository)
}