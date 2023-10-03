package uz.gita.bookappmn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val scope = CoroutineScope(Dispatchers.Main)

        val image = findViewById<ImageView>(R.id.icon)
        val text = findViewById<TextView>(R.id.textSplash)
        image.animate().scaleX(0f).scaleY(0f).setDuration(0).start()
        text.animate().scaleX(0f).scaleY(0f).setDuration(0).start()

        image.animate().scaleX(1.1f).scaleY(1.1f).setDuration(1000).withEndAction {
            image.animate().scaleX(1f).scaleY(1f).setDuration(800).start()
        }.start()

        text.animate().scaleX(1.1f).scaleY(1.1f).setDuration(1000).withEndAction {
            text.animate().scaleX(1f).scaleY(1f).setDuration(800).start()
        }.start()

        scope.launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

    }
}