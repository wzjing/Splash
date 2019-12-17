package com.sample.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    val options = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

    val executor = Executors.newSingleThreadExecutor()

    companion object {
        var isRunning = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            decorView.systemUiVisibility = options
            decorView.setOnSystemUiVisibilityChangeListener {
                executor.run {
                    if (isRunning) return@run
                    isRunning = true
                    Thread.sleep(1000)
                    Handler(Looper.getMainLooper()).post {
                        window.decorView.systemUiVisibility = options
                    }
                    isRunning = false
                }
            }

        }
        setContentView(R.layout.activity_main)

    }
}
