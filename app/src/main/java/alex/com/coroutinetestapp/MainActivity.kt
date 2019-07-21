package alex.com.coroutinetestapp

import alex.com.coroutinetestapp.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }

    private fun startTimer() {
        job?.cancel()
        job = GlobalScope.launch {
            withContext(Dispatchers.Main) {
                while (true) {
                    delay(100)
                    val time = System.currentTimeMillis() / 100
                    val timeString = time.toString().substring(8, 11)

                    binding.mainTv.text = "Time: $timeString"
                }
            }
        }
    }
}
