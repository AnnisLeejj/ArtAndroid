package com.heking.artandroid.Activities

import android.graphics.drawable.TransitionDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.heking.artandroid.R

class FrawableTest : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.start_transition -> {
                var drawable: TransitionDrawable    = iv_show!!.background as TransitionDrawable
                drawable.startTransition(500)
            }
            R.id.reverse_transition -> {
                var drawable: TransitionDrawable    = iv_show!!.background as TransitionDrawable
                drawable.reverseTransition(500)
            }
        }
    }

    var iv_show: ImageView? = null
    var bt_start: Button? = null
    var bt_reverse: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frawable_test)
        iv_show = findViewById(R.id.iv_show)
        bt_start = findViewById(R.id.start_transition)
        bt_reverse = findViewById(R.id.reverse_transition)
        bt_start!!.setOnClickListener(this)
        bt_reverse!!.setOnClickListener(this)
    }

}
