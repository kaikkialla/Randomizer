package com.example.randomizer.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.randomizer.R
import com.example.randomizer.Utils
import com.example.randomizer.repository.MainRepository
import com.example.randomizer.ui.main.MainFragment
import kotlin.math.log


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.f, MainFragment())
                .commit()
        }
    }
}