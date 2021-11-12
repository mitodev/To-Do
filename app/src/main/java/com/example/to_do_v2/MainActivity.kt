package com.example.to_do_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.to_do_v2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //.........................values
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    //.........................functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}