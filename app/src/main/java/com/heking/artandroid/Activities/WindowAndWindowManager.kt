package com.heking.artandroid.Activities

import android.graphics.PixelFormat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.widget.Button
import com.heking.artandroid.R

class WindowAndWindowManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_and_window_manager)
        findViewById<Button>(R.id.add_view).setOnClickListener({
            //给window添加Button
            var button = Button(this)
            button.text = "button"
            var layooutParams = LayoutParams(LayoutParams.WRAP_CONTENT
                    , LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT)
            layooutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
            layooutParams.gravity = Gravity.CENTER
            layooutParams.x = 100
            layooutParams.y = 200
            layooutParams.type = LayoutParams.TYPE_SYSTEM_ERROR
            windowManager.addView(button, layooutParams)
        })
    }
}
