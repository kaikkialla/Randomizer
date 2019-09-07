package com.example.randomizer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.randomizer.R
import com.example.randomizer.ui.main.MainFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val callable: Callable<Long> = Callable {
//            val time = System.currentTimeMillis()
//            Glide.with(this).load("https://i.imgur.com/8SAimda.jpg")
//            System.currentTimeMillis() - time
//        }
//
//        val future = FutureTask<Long>(callable)
//        Thread(future).start()
//        Utils.log(future.get().toString())




        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.f, MainFragment())
                .commit()
        }
    }
}