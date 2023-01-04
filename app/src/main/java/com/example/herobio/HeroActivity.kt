package com.example.herobio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class HeroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)

        val headingHero : TextView =findViewById(R.id.heading)
        val mainHero : TextView = findViewById(R.id.hero)
        val imageHero : ImageView = findViewById(R.id.image_heading)

        val bundle : Bundle?= intent.extras
        val heading = bundle!!.getString("heading")
        val imageId = bundle.getInt("imageId")
        val hero = bundle.getString("hero")

        headingHero.text = heading
        mainHero.text = hero
        imageHero.setImageResource(imageId)
    }
}