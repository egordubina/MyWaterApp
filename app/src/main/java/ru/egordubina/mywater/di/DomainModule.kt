package ru.egordubina.mywater.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.egordubina.mywater.domain.repositories.DailyRepository
import ru.egordubina.mywater.domain.usecases.DailyUseCase
import ru.egordubina.mywater.domain.usecases.DailyUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideDailyWaterUseCase(dailyRepository: DailyRepository): DailyUseCase =
        DailyUseCaseImpl(dailyRepository = dailyRepository)
}