package edu.miu.profileapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var spf: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spf = getSharedPreferences("login", Context.MODE_PRIVATE)
        val email = spf.getString("email", "")
        var pass = spf.getString("pass", "")
        tvEmail.setText(email)
        tvPassword.setText(pass)
    }

    fun login(view: View) {
        var email = tvEmail.text.toString().trim()
        var pass = tvPassword.text.toString().trim()
        if (email.isEmpty()) {
            Toast.makeText(this, "Email is required!", Toast.LENGTH_LONG).show()
            return
        }

        if (pass.isEmpty()) {
            Toast.makeText(this, "Pass is required!", Toast.LENGTH_LONG).show()
            return
        }
        var spe = spf.edit()
        spe.putString("email", email)
        spe.putString("pass", pass)
        spe.apply()
        openHomeActivity()
    }

    fun openHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}