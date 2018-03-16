package com.heking.artandroid.Activities

import android.app.Notification
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.heking.artandroid.R
import android.widget.RemoteViews
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import com.heking.artandroid.MainActivity
import android.content.Intent
import android.os.Build
import android.widget.Button
import android.graphics.BitmapFactory
import android.graphics.Color

class RemoteViewTest : AppCompatActivity() {
    lateinit var manager: NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_view_test)
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        findViewById<Button>(R.id.show).setOnClickListener({
            showRemoteView()
        })
        findViewById<Button>(R.id.update).setOnClickListener({
            updateRemoteView()
        })
        findViewById<Button>(R.id.show_notification).setOnClickListener({
            //弹出  原生 通知
            showNotification()
        })
        findViewById<Button>(R.id.update_notification).setOnClickListener({
            //弹出  原生 通知
            updateNotification()
        })
    }

    var mRemoteViews: RemoteViews? = null
    lateinit var mNotification: Notification
    private fun showRemoteView() {
        //1.创建RemoteViews实例
        mRemoteViews = RemoteViews(packageName, R.layout.remoteview_layout)

        //2.构建一个打开Activity的PendingIntent
        val intent = Intent(this@RemoteViewTest, MainActivity::class.java)
        val mPendingIntent = PendingIntent.getActivity(this@RemoteViewTest, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //3.创建一个Notification
        mNotification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//android 7.0
            Notification.Builder(this)
                    .setContentIntent(mPendingIntent)
                    // .setContent(mRemoteViews)
                    .setCustomBigContentView(mRemoteViews)
                    .build()
        } else {
            Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(mPendingIntent)
                    .setContent(mRemoteViews)
                    .build()
        }
        //4.获取NotificationManager
        //弹出通知
        manager.notify(1, mNotification)
    }

    var first = true
    private fun updateRemoteView() {
        if (mRemoteViews == null) return
        if (first) {
            mRemoteViews!!.setChronometer(R.id.my_chronometer, 0L, "%s", true)
            first = false
        }
        mRemoteViews!!.setTextViewText(R.id.remote_text, "更新$count")
        mRemoteViews!!.setTextColor(R.id.remote_text, Color.GRAY)
        count++
        manager.notify(1, mNotification)
    }

    var count = 1
    var builder: Notification.Builder? = null
    var notification: Notification? = null
    /**
     * 显示原生的notification
     */
    protected fun showNotification() {
        // API 16之前的方式好多都过时
        builder = Notification.Builder(this)
        builder!!.setTicker("Hello World!")// 收到通知的时候用于显示于屏幕顶部通知栏的内容
        builder!!.setSmallIcon(R.mipmap.ic_launcher)// 设置通知小图标,在下拉之前显示的图标
        builder!!.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))// 落下后显示的图标
        builder!!.setWhen(System.currentTimeMillis())
        builder!!.setContentTitle("This is title $count")
        count++
        builder!!.setContentText("Here is content")
        // builder.setSound(uri);//声音提示
        // builder.setSound(sound, streamType);//科设置 streamtype
        // builder.setStyle(style);//style设置
        // http://developer.android.com/reference/android/app/Notification.Style.html
        // builder.setVibrate(long[]);//设置震动
        builder!!.setOngoing(true)// 不能被用户x掉，会一直显示，如音乐播放等
        builder!!.setAutoCancel(true)// 自动取消
        builder!!.setOnlyAlertOnce(true)// 只alert一次
        builder!!.setDefaults(Notification.DEFAULT_ALL)
        builder!!.setContentInfo("额外的内容")// 添加到了右下角
//        val intent = Intent(this, SecondActivity::class.java)
//        intent.putExtra(SINGLE, NATIVE_NOTIFICATION)
//        intent.`package` = this.packageName
        //val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        // builder.setContentIntent(pendingIntent)
        notification = builder!!.build()
        notification!!.flags = Notification.FLAG_AUTO_CANCEL
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, notification)
    }

    /**
     * 更新 原生的notification
     */
    protected fun updateNotification() {
        if (builder == null || notification == null) return
        builder!!.setContentText("更新  $count")
        count++

        //不要这三行不行的
        notification = builder!!.build()
        notification!!.flags = Notification.FLAG_AUTO_CANCEL
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.notify(0, notification)
    }
}
