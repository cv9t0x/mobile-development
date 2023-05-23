package com.example.networkdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.networkdemo.network.NetHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var usernameEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.username_edittext)
        registerButton = findViewById(R.id.register_button)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            if (username == "") {
                Toast.makeText(this@MainActivity, "Enter username!", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val authResponse = NetHandler.authApi.register(AuthBody(username))
                        val editor = getSharedPreferences("my_prefs", Context.MODE_PRIVATE).edit()
                        editor.putString("token", authResponse.token)
                        editor.apply()
                        redirectToCardsActivity()
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(
                                this@MainActivity,
                                "Registration failed: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

        }
    }

    fun redirectToCardsActivity() {
        val intent = Intent(this, CardsActivity::class.java)
        startActivity(intent)
    }
}