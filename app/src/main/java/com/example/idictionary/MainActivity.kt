package com.example.idictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.idictionary.di.AppComponent
import com.example.idictionary.di.DictionaryApp
import com.example.idictionary.view.MainFragment

class MainActivity : AppCompatActivity() {
    lateinit var appComponent :AppComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as DictionaryApp).component
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, MainFragment()).commit()
    }
}