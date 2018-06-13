package com.raqun.peanutdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.raqun.peanut.Peanut
import com.raqun.peanut.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Error

class MainActivity : AppCompatActivity() {

    private lateinit var peanut: Peanut<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peanut = object : Peanut<String>() {
            override fun call() {
                var count = 0
                for (i in 0 until 100000) {
                    count += count
                }
                onSuccess(count.toString())
            }
        }

        peanut.runOn(Schedulers.io())
                .receiveOn(Schedulers.main())
                .run(object : Peanut.Subscriber<String> {

                    override fun onComplete(data: String) {
                        count.text = data
                    }

                    override fun onError(e: Error) {
                        e.printStackTrace()
                    }
                })
    }

    override fun onDestroy() {
        peanut.destroy()
        super.onDestroy()
    }
}
