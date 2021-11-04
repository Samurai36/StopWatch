package viktor.khlebnikov.gb.stopwatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import viktor.khlebnikov.gb.stopwatch.domain.calculate.ElapsedTimeCalculator
import viktor.khlebnikov.gb.stopwatch.domain.calculate.StopwatchStateCalculator
import viktor.khlebnikov.gb.stopwatch.domain.timestamp.TimestampProvider
import viktor.khlebnikov.gb.stopwatch.domain.timestamp.formatter.TimestampMillisecondsFormatter
import viktor.khlebnikov.gb.stopwatch.state.StopwatchListOrchestrator
import viktor.khlebnikov.gb.stopwatch.state.StopwatchStateHolder

class MainViewModel() : ViewModel() {

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )
    val stopwatchListOrchestrator2 = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )

    val liveData: LiveData<String> = stopwatchListOrchestrator.ticker.asLiveData()
    val liveData2: LiveData<String> = stopwatchListOrchestrator2.ticker.asLiveData()

}