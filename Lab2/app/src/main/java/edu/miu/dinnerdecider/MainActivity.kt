package edu.miu.dinnerdecider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    var menus: MutableList<String> = mutableListOf("Hamburger", "Pizza", "Mexican", "American", "Chinese");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun addFood(view: View) {
        val newMenu = etFood.text.toString().trim()
        if (newMenu.isNotEmpty()) {
            menus.add(newMenu)
            etFood.text.clear()
        }
    }

    fun decideMenu(view: View) {
        val random = Random.nextInt(menus.size)
        tvFood.text = menus[random]
    }
}