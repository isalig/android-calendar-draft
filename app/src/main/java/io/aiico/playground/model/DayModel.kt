package io.aiico.playground.model

import android.view.View
import com.xwray.groupie.Item
import io.aiico.playground.R
import io.aiico.playground.adapter.viewholder.DayViewHolder
import org.threeten.bp.LocalDate

data class DayModel(
    val dayText: String?,
    val priceText: String?,
    val localDate: LocalDate
) : Item<DayViewHolder>() {

    override fun createViewHolder(itemView: View) = DayViewHolder(itemView)

    override fun bind(viewHolder: DayViewHolder, position: Int) {
        viewHolder.bind(this)
    }

    override fun getLayout(): Int = R.layout.item_day
}
