package io.aiico.playground.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.xwray.groupie.GroupieViewHolder
import io.aiico.playground.R
import io.aiico.playground.model.DayModel

class DayViewHolder(view: View) : GroupieViewHolder(view) {

    private val dayNumberTextView: TextView = itemView.findViewById(R.id.day_number_text_view)
    private val priceTextView: TextView = itemView.findViewById(R.id.price_text_view)

    fun bind(model: DayModel) {
        with(model) {
            dayNumberTextView.text = dayText
            priceTextView.text = priceText
        }
    }
}
