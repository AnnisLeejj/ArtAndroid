package com.heking.artandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.heking.artandroid.Activities.CircleActivity
import com.heking.artandroid.Activities.FrawableTest
import com.heking.artandroid.Activities.RemoteViewTest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bt_1).setOnClickListener({ startActivity(Intent(this@MainActivity, CircleActivity::class.java)) })
        findViewById<Button>(R.id.bt_2).setOnClickListener({ startActivity(Intent(this@MainActivity, RemoteViewTest::class.java)) })
        findViewById<Button>(R.id.bt_3).setOnClickListener({ startActivity(Intent(this@MainActivity, FrawableTest::class.java)) })
    }
}


