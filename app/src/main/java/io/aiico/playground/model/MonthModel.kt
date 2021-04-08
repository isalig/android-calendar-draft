package io.aiico.playground.model

import android.view.View
import com.xwray.groupie.Item
import io.aiico.playground.R
import io.aiico.playground.adapter.viewholder.MonthViewHolder

data class MonthModel(val title: String) : Item<MonthViewHolder>() {

    override fun createViewHolder(itemView: View) = MonthViewHolder(itemView)

    override fun bind(viewHolder: MonthViewHolder, position: Int) {
        viewHolder.bind(this)
    }

    override fun getLayout(): Int = R.layout.item_month
}
