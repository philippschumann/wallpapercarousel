package com.philippschumann.wallpapercarousel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var callback: FABClickedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            callback.fabClicked()
        }
    }


    interface FABClickedListener {
        fun fabClicked()
    }

    fun setFABClickListener(callback: FABClickedListener) {
        this.callback = callback
    }
}