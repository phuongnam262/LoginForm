package com.example.loginform

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.loginform.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    // Use view binding to reference your layout
    private lateinit var mActivityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        //Cách 1
        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMainBinding.root)


        //Cách 2:
//        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val mainViewModel = MainViewModel(name = "TinCoder Android Kotlin")
        mActivityMainBinding.mainViewModel = mainViewModel
        mActivityMainBinding.lifecycleOwner = this



        // MyFirebaseMessage
        FirebaseMessaging.getInstance().subscribeToTopic("News")
            .addOnSuccessListener {
                println("Subscribe success")
            }
            .addOnFailureListener {
                println("Subscribe fail")
            }

    }
}
