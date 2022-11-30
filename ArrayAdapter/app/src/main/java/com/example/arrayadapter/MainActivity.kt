package com.example.arrayadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import java.util.Random

class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var names: ArrayList<String>
    lateinit var surnames: ArrayList<String>
    lateinit var users: ArrayList<User>
    lateinit var adapter: UserListAdapter
    private val r = Random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = resources.getStringArray(R.array.names).toCollection(ArrayList())
        surnames = resources.getStringArray(R.array.surnames).toCollection(ArrayList())

        listView = findViewById(R.id.list)
        users = ArrayList()

        adapter = UserListAdapter(this, users)

        listView.adapter = adapter
    }

    private fun createUser() {
        val name = names[r.nextInt(names.size)]
        val surname = surnames[r.nextInt(surnames.size)]
        val user = User(name, surname)
        users.add(user)
    }

    fun onClick(view: View) {
        createUser()
        adapter.notifyDataSetChanged()
    }
}