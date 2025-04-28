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
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val channelId1 = "Chanel1"
    private val channelId2 = "Chanel2"
    private val title_push="What is Lorem Ipsum?\n"
    private val content_push=" when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, " +
            "but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised " +
            "in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishin"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//
//        val btnSendNotification1 = findViewById<Button>(R.id.btn_send_notification1)
//        btnSendNotification1.setOnClickListener {
//            sendNotification1()
//        }
//
//        val btnSendNotification2 = findViewById<Button>(R.id.btn_send_notification2)
//        btnSendNotification2.setOnClickListener {
//            sendNotification2()
//        }

        // MyFirebaseMessage
        FirebaseMessaging.getInstance().subscribeToTopic("News")
            .addOnSuccessListener {
                println("Subscribe success")
            }
            .addOnFailureListener {
                println("Subscribe fail")
            }

    }

    private fun sendNotification1() {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)

        val builder = NotificationCompat.Builder(this, channelId1) // dùng NotificationCompat.Builder

        //Set âm thanh
        builder.setDefaults(Notification.DEFAULT_SOUND)

        val notification = builder
            .setContentTitle(title_push)
            .setContentText(content_push)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(content_push)
            )
            .setSmallIcon(R.drawable.img)
            .setLargeIcon(bitmap)
            .setColor(ContextCompat.getColor(this, R.color.teal_200))
            .build()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationId(), notification)
    }

    private fun sendNotification2() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.sky)

        val builder = NotificationCompat.Builder(this, channelId2) // cũng dùng NotificationCompat.Builder

        val notification = builder
            .setContentTitle("Bye!")
            .setContentText("This is a chanel 2 notification.")
            .setSmallIcon(R.drawable.img)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)
            )
            .setLargeIcon(bitmap)
            .setColor(ContextCompat.getColor(this, R.color.teal_200))
            .build()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationId(), notification)
    }

    private fun getNotificationId(): Int {
        return (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
    }
}