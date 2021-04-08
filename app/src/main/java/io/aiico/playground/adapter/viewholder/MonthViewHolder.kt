package io.aiico.playground.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.xwray.groupie.GroupieViewHolder
import io.aiico.playground.R
import io.aiico.playground.model.MonthModel

class MonthViewHolder(view: View) : GroupieViewHolder(view) {

    private val titleTextView: TextView = view.findViewById(R.id.title_text_view)

    fun bind(headerModel: MonthModel) {
        titleTextView.text = headerModel.title
    }
}
