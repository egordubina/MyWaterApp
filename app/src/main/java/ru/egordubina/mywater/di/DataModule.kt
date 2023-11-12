package ru.egordubina.mywater.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.egordubina.mywater.data.repositories.DailyRepositoryImpl
import ru.egordubina.mywater.domain.repositories.DailyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(produceFile = { context.preferencesDataStoreFile("MyWaterPreferences") })

    @Singleton
    @Provides
    fun provideDailyRepository(dataStore: DataStore<Preferences>): DailyRepository =
        DailyRepositoryImpl(dataStore = dataStore)
}