package com.example.loginform

import android.app.Notification
import android.app.NotificationManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val channelId1 = "Chanel1"
    private val channelId2 = "Chanel2"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Tạo notification channels nếu cần
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//
//            val channel1 = android.app.NotificationChannel(
//                channelId1,
//                "Channel 1",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            notificationManager.createNotificationChannel(channel1)
//
//            val channel2 = android.app.NotificationChannel(
//                channelId2,
//                "Channel 2",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            notificationManager.createNotificationChannel(channel2)
//        }

        val btnSendNotification1 = findViewById<Button>(R.id.btn_send_notification1)
        btnSendNotification1.setOnClickListener {
            sendNotification1()
        }

        val btnSendNotification2 = findViewById<Button>(R.id.btn_send_notification2)
        btnSendNotification2.setOnClickListener {
            sendNotification2()
        }
    }

    private fun sendNotification1() {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)

        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, channelId1)
        } else {
            Notification.Builder(this)
        }

        val notification = builder
            .setContentTitle("Hello!")
            .setContentText("This is a simple push notification.")
            .setSmallIcon(R.drawable.img) // bạn có thể dùng ic_launcher nếu chưa có
            .setLargeIcon(bitmap)
            .setColor(ContextCompat.getColor(this, R.color.teal_200)) // hoặc bất kỳ màu nào bạn muốn
            .build()

        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationId(), notification)
    }

    private fun sendNotification2() {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)

        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, channelId2)
        } else {
            Notification.Builder(this)
        }

        val notification = builder
            .setContentTitle("Bye!")
            .setContentText("This is a chanel 2 notification.")
            .setSmallIcon(R.drawable.img) // bạn có thể dùng ic_launcher nếu chưa có
            .setLargeIcon(bitmap)
            .setColor(ContextCompat.getColor(this, R.color.teal_200)) // hoặc bất kỳ màu nào bạn muốn
            .build()

        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationId(), notification)
    }

    private fun getNotificationId(): Int {
        return (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
    }
}