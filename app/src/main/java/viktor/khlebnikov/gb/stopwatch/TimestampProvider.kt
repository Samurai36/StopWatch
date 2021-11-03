package viktor.khlebnikov.gb.stopwatch

interface TimestampProvider {
    fun getMilliseconds(): Long
}