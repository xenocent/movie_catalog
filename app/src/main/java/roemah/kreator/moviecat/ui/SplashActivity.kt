package roemah.kreator.moviecat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import roemah.kreator.moviecat.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    // Splash screen timer
    private val SPLASH_TIME_OUT = 1200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
        val intent = Intent(this, HomeActivity::class.java)
        Thread.sleep(SPLASH_TIME_OUT.toLong())
        startActivity(intent)
        finish()
    }
}