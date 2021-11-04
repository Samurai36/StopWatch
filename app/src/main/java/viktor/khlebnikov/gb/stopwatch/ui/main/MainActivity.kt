package viktor.khlebnikov.gb.stopwatch.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import viktor.khlebnikov.gb.stopwatch.MainViewModel
import viktor.khlebnikov.gb.stopwatch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ViewModelProvider(this).get(MainViewModel::class.java).liveData.observe(
            this
        ) { dataFromDataBase ->
            binding.textTime.text = dataFromDataBase
            binding.buttonStart.setOnClickListener {
                ViewModelProvider(this).get(MainViewModel::class.java).stopwatchListOrchestrator.start()
            }
            binding.buttonPause.setOnClickListener {
                ViewModelProvider(this).get(MainViewModel::class.java).stopwatchListOrchestrator.pause()
            }
            binding.buttonStop.setOnClickListener {
                ViewModelProvider(this).get(MainViewModel::class.java).stopwatchListOrchestrator.stop()
            }
        }

        ViewModelProvider(this).get(MainViewModel::class.java).liveData2.observe(
            this
        ) { dataFromDataBase ->
            binding.textTime2.text = dataFromDataBase
            binding.buttonStart2.setOnClickListener {
                ViewModelProvider(this).get(MainViewModel::class.java).stopwatchListOrchestrator2.start()
            }
            binding.buttonPause2.setOnClickListener {
                ViewModelProvider(this).get(MainViewModel::class.java).stopwatchListOrchestrator2.pause()
            }
            binding.buttonStop2.setOnClickListener {
                ViewModelProvider(this).get(MainViewModel::class.java).stopwatchListOrchestrator2.stop()
            }
        }

    }
}