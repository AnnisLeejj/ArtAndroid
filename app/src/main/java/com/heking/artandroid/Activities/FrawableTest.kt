package com.heking.artandroid.Activities

import android.animation.AnimatorSet
import android.animation.IntEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.TransitionDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.widget.Button
import android.widget.ImageView
import com.heking.artandroid.R

class FrawableTest : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.start_transition -> {
                var drawable: TransitionDrawable = iv_show!!.background as TransitionDrawable
                drawable.startTransition(500)
            }
            R.id.reverse_transition -> {
                var drawable: TransitionDrawable = iv_show!!.background as TransitionDrawable
                drawable.reverseTransition(500)
            }
            R.id.start_layout_animation -> {

                //属性动画(这个属性要有  set/get)
                // ObjectAnimator.ofInt(tb_show, "width", tb_show!!.width, 400)
//                ObjectAnimator.ofInt(tb_show, "BackgroundColor", 0xFF00FF).setDuration(500).start()
//                tb_show!!.setBackgroundColor()
//                var set = AnimatorSet()
//                set.playTogether(ObjectAnimator.ofInt(tb_show, "textColor", Color.BLACK, Color.YELLOW).setDuration(500),
//                        ObjectAnimator.ofFloat(tb_show, "textSize", 30F))// tb_show!!.setTextSize(30F)
//                set.duration = 1000
//                set.start()


                //某些没有set/get的属性  通过ValueAnimator 来动态设置
                var width_start = tb_show!!.width
                var width_end: Int = (width_start * 1.4).toInt()
                var color_start = Color.WHITE
                var color_end: Int = Color.YELLOW
                var mEvaluator = IntEvaluator()
                var valueAnimator = ValueAnimator.ofInt(1, 100)

                valueAnimator.addUpdateListener {
                    //获取当前动画的进度值   Int 0-100
                    var currentValue = it.animatedValue
                    //获取当前进度占整个动画过程的比例  Float 0-1之间
                    var fraction = it.animatedFraction

                    //直接调用整形估值器,通过比例计算出宽度,然后再设置给Button
                    tb_show!!.layoutParams.width = mEvaluator.evaluate(fraction, width_start, width_end)
                    tb_show!!.setTextColor(mEvaluator.evaluate(fraction, color_start, color_end))
                    tb_show!!.requestLayout()
                }
                valueAnimator.setDuration(1000).start()
            }
        }
    }

    var iv_show: ImageView? = null
    var bt_start: Button? = null
    var bt_reverse: Button? = null
    var start_layout_animation: Button? = null
    var tb_show: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frawable_test)
        iv_show = findViewById(R.id.iv_show)
        bt_start = findViewById(R.id.start_transition)
        bt_reverse = findViewById(R.id.reverse_transition)
        start_layout_animation = findViewById(R.id.start_layout_animation)
        tb_show = findViewById(R.id.tb_show)
        bt_start!!.setOnClickListener(this)
        bt_reverse!!.setOnClickListener(this)
        start_layout_animation!!.setOnClickListener(this)
    }
}
