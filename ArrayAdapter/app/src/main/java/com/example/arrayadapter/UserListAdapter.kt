package com.example.arrayadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class UserListAdapter(private val ctx: Context, private val users: ArrayList<User>) : BaseAdapter() {

    override fun getCount(): Int {
        return users.size
    }

    override fun getItem(pos: Int): User {
        return users[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(pos: Int, _convertView: View?, parent: ViewGroup?): View {
        val user = users[pos]
        var convertView = _convertView

        convertView = LayoutInflater.from(ctx).inflate(R.layout.user, parent, false)

        val nameTv: TextView = convertView.findViewById(R.id.name)
        val surnameTv: TextView = convertView.findViewById(R.id.surname)

        nameTv.text = user.name
        surnameTv.text = user.surname

        return convertView
    }

}