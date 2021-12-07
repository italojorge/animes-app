package br.com.animes.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import br.com.animes.R
import kotlinx.coroutines.delay

class SplashScreenActivity : AppCompatActivity() {
    private companion object {
        const val SPLASH_ANIMATION_DURATION = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycle.coroutineScope.launchWhenCreated {
            delay(SPLASH_ANIMATION_DURATION)
            startActivity(Intent(this@SplashScreenActivity, AppActivity::class.java))
            finish()
        }
    }
}
