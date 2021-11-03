package viktor.khlebnikov.gb.stopwatch

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlin.random.Random

internal data class Data(val data: String)

internal object DataBase {

    fun fetchData() = Random.nextInt()
}

internal class DataSource(
    private val dataBase: DataBase = DataBase,
    private val refreshIntervalMs: Long = 1000
) {
    val data: Flow<String> = flow {
        while (true) {
            val dataFromDataBase = dataBase.fetchData()
            emit(dataFromDataBase.toString())
            delay(refreshIntervalMs)
        }
    }
        .flowOn(Dispatchers.Default)
        .catch { e ->
            println(e.message)//Error!
        }
}
internal class Repository(dataSource: DataSource = DataSource()) {

    val userData: Flow<Data> =
        dataSource.data.map { data -> Data(data) }
    //.onEach { saveInCache(it) }
}

internal class MainViewModel(
    repository: Repository = Repository()
) : ViewModel() {

    val liveData: LiveData<Data> = repository.userData.asLiveData()

//    val liveData: MutableLiveData<Data> = MutableLiveData()
//
//    init {
//        viewModelScope.launch {
//            repository.userData.flowOn(Dispatchers.Main)
//                .collect { data ->
//                    liveData.value = data
//                }
//        }
//    }
}