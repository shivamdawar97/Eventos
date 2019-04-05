package com.example.shivam97.eventos.mainFragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.shivam97.eventos.R
import com.example.shivam97.eventos.addeventsclasses.AddEvent
import kotlinx.android.synthetic.main.a_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navControler:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddEvent::class.java))

        }

         navControler= Navigation.findNavController(this,R.id.container_frame)
        //bottom_navigation.setupWithNavController(navControler)
        setupBottomNavMenu(navControler)

    }

    private fun setupBottomNavMenu(navController: NavController){

        bottom_navigation?.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }



}
