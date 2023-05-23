package com.example.networkdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class CardsAdapter(private val list: List<Card>) : RecyclerView.Adapter<CardsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardIdTextView: TextView
        private val cardCountTextView: TextView
        private val cardColorTextView: TextView
        private val cardShapeTextView: TextView
        private val cardFillTextView: TextView

        init {
            cardIdTextView = itemView.findViewById(R.id.card_id)
            cardCountTextView = itemView.findViewById(R.id.card_count)
            cardColorTextView = itemView.findViewById(R.id.card_color)
            cardShapeTextView = itemView.findViewById(R.id.card_shape)
            cardFillTextView = itemView.findViewById(R.id.card_fill)
        }

        fun bindTo(card: Card) {
            card.apply {
                cardIdTextView.text = "id: (${id})"
                cardCountTextView.text = "count: (${count})"
                cardColorTextView.text = "color: (${color})"
                cardShapeTextView.text = "shape: (${shape})"
                cardFillTextView.text = "fill: (${fill})"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bindTo(list[pos])
    }

}