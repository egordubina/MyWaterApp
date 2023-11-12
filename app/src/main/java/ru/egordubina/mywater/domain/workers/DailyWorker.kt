package ru.egordubina.mywater.domain.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.egordubina.mywater.domain.repositories.DailyRepository

@HiltWorker
class DailyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val dailyRepository: DailyRepository
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                dailyRepository.updateDailyCurrentData(0)
                Result.success()
            } catch (_: Exception) {
                Result.failure()
            }
        }
    }
}