package com.theblackbit.animemania.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.theblackbit.animemania.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindgin: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindgin = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindgin.root)
    }
}
