package com.example.idictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.idictionary.view.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, MainFragment()).commit()
    }
}