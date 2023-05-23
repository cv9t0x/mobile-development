package com.example.networkdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkdemo.databinding.ActivityCardsBinding
import com.example.networkdemo.network.NetHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardsActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CardsActivity"
    }

    private lateinit var binding: ActivityCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCardsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.root.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cardsResponse = NetHandler.cardsApi.getAll()
                val cards = cardsResponse.board
                runOnUiThread {
                    binding.root.adapter = CardsAdapter(cards as List<Card>)
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@CardsActivity,
                    "Cards fetch: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}