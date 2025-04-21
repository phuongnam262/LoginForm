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

    private val channelId = "my_channel_id"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnSendNotification = findViewById<Button>(R.id.btn_send_notification)
        btnSendNotification.setOnClickListener {
            sendNotification()
        }

    }
    private fun sendNotification() {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)

        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, channelId)
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

    private fun getNotificationId(): Int {
        return (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
    }
}