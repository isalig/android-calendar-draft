package io.aiico.playground.model

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.aiico.playground.R
import io.aiico.playground.databinding.ItemWeekBinding
import io.aiico.playground.view.DayView

class WeekItem(val days: List<DayModel>) : BindableItem<ItemWeekBinding>() {

    override fun getLayout(): Int = R.layout.item_week

    override fun initializeViewBinding(view: View) = ItemWeekBinding.bind(view)

    override fun bind(viewBinding: ItemWeekBinding, position: Int) {
        with(viewBinding) {
            listOf<DayView>(monday, tuesday, wednesday, thursday, friday, saturday, sunday)
                .forEachIndexed { index, dayView -> dayView.bind(days[index]) }
        }
    }
}
