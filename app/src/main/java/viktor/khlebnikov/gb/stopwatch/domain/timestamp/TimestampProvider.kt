package viktor.khlebnikov.gb.stopwatch.domain.timestamp

interface TimestampProvider {
    fun getMilliseconds(): Long
}