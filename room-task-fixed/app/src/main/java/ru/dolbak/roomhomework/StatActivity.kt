package ru.dolbak.roomhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room

class StatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "results.db"
        ).build()

        db.resultsDao().getTotalCapitalization().observe(this) { totalCapitalization ->
            findViewById<TextView>(R.id.money).text = totalCapitalization.toString()
        }

        db.resultsDao().getCompaniesAboveAverageCapitalization().observe(this) { count ->
            findViewById<TextView>(R.id.good).text = count.toString()
        }

        db.resultsDao().getEnglishCompaniesCount().observe(this) { count ->
            findViewById<TextView>(R.id.english).text = count.toString()
        }

        db.resultsDao().getBestCompany().observe(this) { company ->
            findViewById<TextView>(R.id.best).text = company.name
        }

        db.resultsDao().getLongestCompanyName().observe(this) { name ->
            findViewById<TextView>(R.id.longest).text = name
        }
    }
}